package com.developertools.questionoverflow.dto.converter;

import com.developertools.questionoverflow.dto.QuestionDto;
import com.developertools.questionoverflow.model.Question;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class QuestionDtoConverter {

    private final UserDtoConverter userDtoConverter;
    private final CategoryDtoConverter categoryDtoConverter;

    public QuestionDtoConverter(UserDtoConverter userDtoConverter,
                                CategoryDtoConverter categoryDtoConverter) {
        this.userDtoConverter = userDtoConverter;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    public QuestionDto convertQuestionToQuestionDto(Question from) {
        return new QuestionDto(
                from.getTitle(),
                from.getDescription(),
                from.isDone(),
                from.getPublicId(),
                from.getLikedNumber(),
                from.getUrlToImages(),
                from.getTags(),
                categoryDtoConverter.convertCategoryToCategoryDto(from.getCategory()),
                userDtoConverter.convertUserToUserDto(from.getUser()),
                from.getCreateDate(),
                from.getUpdateDate()
        );
    }

    public List<QuestionDto> convertQuestionListToQuestionDtoList(List<Question> from) {
        return from.stream().map(this::convertQuestionToQuestionDto).collect(Collectors.toList());
    }

}
