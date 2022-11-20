package com.developertools.questionoverflow.dto.converter;

import com.developertools.questionoverflow.dto.UserDto;
import com.developertools.questionoverflow.model.User;
import org.springframework.stereotype.Component;

import java.util.*;
import java.util.stream.Collectors;

@Component
public class UserDtoConverter {

    public UserDto convertUserToUserDto(User from) {
        final Set<String> publicIdOfLikedQuestions = new HashSet<>();
        final Set<String> links = new HashSet<>();

        from.getPublicIdOfLikedQuestions()
                .stream()
                .map(likedQuestion -> publicIdOfLikedQuestions.add(likedQuestion.getPublicIdOfLikedQuestions()));
        from.getLinks()
                .stream()
                .map(link -> links.add(link.getLink()));

        return new UserDto(
                from.getUsername(),
                from.getMail(),
                from.isActive(),
                from.getUrlToImage(),
                publicIdOfLikedQuestions,
                links,
                from.getCreateDate(),
                from.getUpdateDate(),
                from.isNotificationPermission()
        );
    }

    public List<UserDto> convertUserToUserDtoList(List<User> from) {
        return from.stream().map(this::convertUserToUserDto).collect(Collectors.toList());
    }

}
