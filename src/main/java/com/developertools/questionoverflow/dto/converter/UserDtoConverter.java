package com.developertools.questionoverflow.dto.converter;

import com.developertools.questionoverflow.dto.UserDto;
import com.developertools.questionoverflow.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convertUserToUserDto(User from) {

        return new UserDto(
                from.getUsername(),
                from.getMail(),
                from.isActive(),
                from.getUrlToImage(),
                from.getPublicIdOfLikedQuestions(),
                from.getLinks(),
                from.getCreateDate(),
                from.getUpdateDate(),
                from.isNotificationPermission(),
                from.getTotalReportNumber()
        );
    }

    public List<UserDto> convertUserToUserDtoList(List<User> from) {
        return from.stream().map(this::convertUserToUserDto).collect(Collectors.toList());
    }

}
