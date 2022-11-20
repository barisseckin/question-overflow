package com.developertools.questionoverflow.dto.converter;

import com.developertools.questionoverflow.dto.CommentDto;
import com.developertools.questionoverflow.model.Comment;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CommentDtoConverter {

    private final QuestionDtoConverter questionDtoConverter;
    private final UserDtoConverter userDtoConverter;

    public CommentDtoConverter(QuestionDtoConverter questionDtoConverter,
                               UserDtoConverter userDtoConverter) {
        this.questionDtoConverter = questionDtoConverter;
        this.userDtoConverter = userDtoConverter;
    }

    public CommentDto convertCommentToCommentDto(Comment from) {
        return new CommentDto(
                from.getBody(),
                from.getLikedNumber(),
                from.getPublicId(),
                from.isApproved(),
                from.getUrlToImages(),
                questionDtoConverter.convertQuestionToQuestionDto(from.getQuestion()),
                userDtoConverter.convertUserToUserDto(from.getUser()),
                from.getCreateDate(),
                from.getUpdateDate()
        );
    }

    public List<CommentDto> convertCommentListToCommentDtoList(List<Comment> from) {
        return from.stream().map(this::convertCommentToCommentDto).collect(Collectors.toList());
    }
}
