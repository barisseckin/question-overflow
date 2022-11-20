package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.QuestionDto;
import com.developertools.questionoverflow.dto.converter.QuestionDtoConverter;
import com.developertools.questionoverflow.dto.request.CreateQuestionRequest;
import com.developertools.questionoverflow.model.*;
import com.developertools.questionoverflow.repository.QuestionRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@Slf4j
public class QuestionService {

    private final QuestionRepository questionRepository;
    private final QuestionDtoConverter questionDtoConverter;
    private final UserService userService;
    private final CategoryService categoryService;

    public QuestionService(QuestionRepository questionRepository, QuestionDtoConverter questionDtoConverter,
                           UserService userService, CategoryService categoryService) {
        this.questionRepository = questionRepository;
        this.questionDtoConverter = questionDtoConverter;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public QuestionDto save(CreateQuestionRequest request) {
        Category category = categoryService.getCategoryByName(request.getCategoryName());
        User user = userService.getByMail(request.getUserMail());

        List<Link> urlToImages = new ArrayList<>();
        List<Tag> tags = new ArrayList<>();

        for (String url : request.getUrlToImages()) {
            urlToImages.add(new Link(url));
        }
        for (String tag : request.getTags()) {
            tags.add(new Tag(tag));
        }

        Question saved = new Question(
                request.getTitle(),
                request.getDescription(),
                urlToImages,
                tags,
                category,
                user
        );

        if (user.isActive()) {
            return questionDtoConverter.convertQuestionToQuestionDto(questionRepository.save(saved));
        }

        throw new RuntimeException("");
    }

    public void delete(String publicId) {
        questionRepository.deleteById(getQuestionByPublicId(publicId).getId());
    }

    public QuestionDto likeQuestion(String publicId) {
        Question fromDbQuestion = getQuestionByPublicId(publicId);
        fromDbQuestion.setLikedNumber(fromDbQuestion.getLikedNumber() + 1);

        return questionDtoConverter.convertQuestionToQuestionDto(questionRepository.save(fromDbQuestion));
    }

    public QuestionDto dislikeQuestion(String publicId) {
        Question fromDbQuestion = getQuestionByPublicId(publicId);
        fromDbQuestion.setLikedNumber(fromDbQuestion.getLikedNumber() - 1);

        return questionDtoConverter.convertQuestionToQuestionDto(questionRepository.save(fromDbQuestion));
    }

    public QuestionDto updateIsDoneStatusByPublicId(String publicId, boolean isDone) {
        Question fromDbQuestion = getQuestionByPublicId(publicId);
        fromDbQuestion.setDone(isDone);

        return questionDtoConverter.convertQuestionToQuestionDto(questionRepository.save(fromDbQuestion));
    }

    protected Question getQuestionByPublicId(String publicId) {
        return questionRepository.findQuestionByPublicId(publicId)
                .orElseThrow(() -> new RuntimeException(""));
    }
}