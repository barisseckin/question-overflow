package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.CommentDto;
import com.developertools.questionoverflow.dto.converter.CommentDtoConverter;
import com.developertools.questionoverflow.dto.request.CreateCommentRequest;
import com.developertools.questionoverflow.dto.request.ReportRequest;
import com.developertools.questionoverflow.dto.request.SendMailRequest;
import com.developertools.questionoverflow.exception.generic.NotFoundException;
import com.developertools.questionoverflow.exception.user.UserNotActiveException;
import com.developertools.questionoverflow.model.Comment;
import com.developertools.questionoverflow.model.Link;
import com.developertools.questionoverflow.model.Question;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.model.enums.ReportType;
import com.developertools.questionoverflow.repository.CommentRepository;
import com.developertools.questionoverflow.utils.log.service.LogService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class CommentService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final QuestionService questionService;
    private final UserService userService;
    private final MailService mailService;
    private final ReportService reportService;
    private final LogService logService;

    public CommentService(CommentRepository commentRepository, CommentDtoConverter commentDtoConverter,
                          QuestionService questionService, UserService userService,
                          MailService mailService, ReportService reportService, LogService logService) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.questionService = questionService;
        this.userService = userService;
        this.mailService = mailService;
        this.reportService = reportService;
        this.logService = logService;
    }

    public CommentDto save(CreateCommentRequest request) {
        Question question = questionService.getQuestionByPublicId(request.getQuestionPublicId());
        User user = userService.getByMail(request.getUserMail());

        List<Link> images = new ArrayList<>();

        for (String image : request.getUrlToImages()) {
            images.add(new Link(image));
        }

        Comment saved = new Comment(
                request.getBody(),
                images,
                question,
                user
        );

        if (user.isActive()) {
            return commentDtoConverter.convertCommentToCommentDto(commentRepository.save(saved));
        }

        throw new UserNotActiveException("user not active, mail: " + user.getMail());
    }

    public void delete(String publicId) {
        commentRepository.deleteById(getCommentByPublicId(publicId).getId());
    }

    public CommentDto likeComment(String publicId) {
        Comment fromDbComment = getCommentByPublicId(publicId);
        fromDbComment.setLikedNumber(fromDbComment.getLikedNumber() + 1);

        return commentDtoConverter.convertCommentToCommentDto(commentRepository.save(fromDbComment));
    }

    public CommentDto dislikeComment(String pubicId) {
        Comment fromDbComment = getCommentByPublicId(pubicId);
        fromDbComment.setLikedNumber(fromDbComment.getLikedNumber() - 1);

        return commentDtoConverter.convertCommentToCommentDto(commentRepository.save(fromDbComment));
    }

    public CommentDto approveComment(String publicId) {
        Comment fromDbComment = getCommentByPublicId(publicId);
        fromDbComment.setApproved(true);
        fromDbComment.setUpdateDate(LocalDateTime.now());

        return commentDtoConverter.convertCommentToCommentDto(commentRepository.save(fromDbComment));
    }

    public CommentDto reportComment(ReportRequest request) {
        Comment fromDbComment = getCommentByPublicId(request.getPublicId());
        fromDbComment.setReportNumber(fromDbComment.getReportNumber() + 1);
        userService.updateTotalReportNumberByMail(fromDbComment.getUser().getMail());
        reportService.save(request, ReportType.COMMENT);

        if (fromDbComment.getReportNumber() >= 50) {
            commentRepository.deleteById(fromDbComment.getId());

            if (fromDbComment.getUser().isNotificationPermission()) {
                mailService.send(new SendMailRequest("Your Comment Deleted!",
                        "Your comment was deleted because it was reported too much! Your deleted comment: "
                                + fromDbComment.getBody(), fromDbComment.getUser().getMail()));
            }

            logService.save(request + " comment deleted");
        }

        else {
            return commentDtoConverter.convertCommentToCommentDto(commentRepository.save(fromDbComment));
        }

        return null;
    }

    private Comment getCommentByPublicId(String publicId) {
        return commentRepository.findCommentByPublicId(publicId)
                .orElseThrow(() -> new NotFoundException("comment not found, publicId: " + publicId));
    }
}
