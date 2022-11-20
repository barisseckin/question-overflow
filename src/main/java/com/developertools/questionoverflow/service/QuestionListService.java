package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.QuestionDto;
import com.developertools.questionoverflow.dto.converter.QuestionDtoConverter;
import com.developertools.questionoverflow.model.Category;
import com.developertools.questionoverflow.model.User;
import com.developertools.questionoverflow.repository.QuestionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class QuestionListService {

    private final QuestionRepository questionRepository;
    private final QuestionDtoConverter questionDtoConverter;
    private final UserService userService;
    private final CategoryService categoryService;

    public QuestionListService(QuestionRepository questionRepository,
                               QuestionDtoConverter questionDtoConverter,
                               UserService userService, CategoryService categoryService) {
        this.questionRepository = questionRepository;
        this.questionDtoConverter = questionDtoConverter;
        this.userService = userService;
        this.categoryService = categoryService;
    }

    public List<QuestionDto> getQuestionByCategoryName(String categoryName) {
        Category category = categoryService.getCategoryByName(categoryName);

        return questionDtoConverter.convertQuestionListToQuestionDtoList(
                questionRepository.findQuestionByCategory(category));
    }

    public List<QuestionDto> getQuestionByUserMail(String userMail) {
        User user = userService.getByMail(userMail);

        return questionDtoConverter.convertQuestionListToQuestionDtoList(
                questionRepository.findQuestionByUser(user)
        );
    }

    public List<QuestionDto> getQuestionByDescription(String description) {
        return questionDtoConverter.convertQuestionListToQuestionDtoList(
                questionRepository.findAll()
                        .stream()
                        .filter(question -> question.getDescription().contains(description))
                        .collect(Collectors.toList())
        );
    }

    public List<QuestionDto> getByLikedNumber(int likedNumber) {
        return questionDtoConverter.convertQuestionListToQuestionDtoList(
                questionRepository.findAll()
                        .stream()
                        .filter(question -> question.getLikedNumber() >= likedNumber)
                        .collect(Collectors.toList())
        );
    }

    public List<QuestionDto> generalQuestionFilter(String categoryName, String description, int likedNumber, String tags) {
        return questionDtoConverter.convertQuestionListToQuestionDtoList(
                questionRepository.findAll()
                        .stream()
                        .filter(question -> question.getCategory().getName().contains(categoryName)
                                && question.getDescription().contains(description)
                                && question.getLikedNumber() >= likedNumber
                                && question.getTags().contains(tags))
                        .collect(Collectors.toList())
        );
    }
}
