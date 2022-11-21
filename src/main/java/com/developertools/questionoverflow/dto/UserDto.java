package com.developertools.questionoverflow.dto;

import com.developertools.questionoverflow.model.LikedQuestion;
import com.developertools.questionoverflow.model.Link;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class UserDto {
    private String username;
    private String mail;
    private boolean isActive;
    private String urlToImage;
    private List<LikedQuestion> publicIdOfLikedQuestions;
    private List<Link> links;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private boolean notificationPermission;
    private int totalReportNumber;
}
