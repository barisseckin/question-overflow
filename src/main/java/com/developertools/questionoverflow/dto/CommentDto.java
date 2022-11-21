package com.developertools.questionoverflow.dto;

import com.developertools.questionoverflow.model.Link;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class CommentDto {
    private String body;
    private int likedNumber;
    private String publicId;
    private boolean approved;
    private List<Link> urlToImages;
    private QuestionDto question;
    private UserDto user;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
    private int reportNumber;
}
