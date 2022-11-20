package com.developertools.questionoverflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.Set;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String mail;
    private boolean isActive;
    private String urlToImage;
    private Set<String> publicIdOfLikedQuestions;
    private Set<String> links;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean notificationPermission;
}
