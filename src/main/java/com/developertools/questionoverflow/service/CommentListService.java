package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.CommentDto;
import com.developertools.questionoverflow.dto.converter.CommentDtoConverter;
import com.developertools.questionoverflow.exception.generic.NotFoundException;
import com.developertools.questionoverflow.model.Question;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.repository.CommentRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CommentListService {

    private final CommentRepository commentRepository;
    private final CommentDtoConverter commentDtoConverter;
    private final QuestionService questionService;
    private final UserService userService;

    public CommentListService(CommentRepository commentRepository,
                              CommentDtoConverter commentDtoConverter,
                              QuestionService questionService, UserService userService) {
        this.commentRepository = commentRepository;
        this.commentDtoConverter = commentDtoConverter;
        this.questionService = questionService;
        this.userService = userService;
    }

    public List<CommentDto> getCommentListByQuestionPublicId(String questionPublicId) {
        Question question = questionService.getQuestionByPublicId(questionPublicId);

        return commentDtoConverter.convertCommentListToCommentDtoList(
                commentRepository.findCommentByQuestion(question)
        );
    }

    public CommentDto getByPublicId(String publicId) {
        return commentDtoConverter.convertCommentToCommentDto(
                commentRepository.findCommentByPublicId(publicId)
                        .orElseThrow(() -> new NotFoundException("comment not found, publicId: " + publicId))
        );
    }

    public List<CommentDto> getByUserMail(String userMail) {
        User user = userService.getByMail(userMail);

        return commentDtoConverter.convertCommentListToCommentDtoList(
                commentRepository.findCommentByUser(user)
        );
    }

}
