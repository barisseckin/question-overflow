package com.developertools.questionoverflow.dto;

import com.developertools.questionoverflow.model.Link;
import com.developertools.questionoverflow.model.Tag;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class QuestionDto {
    private String title;
    private String description;
    private boolean isDone;
    private String publicId;
    private int likedNumber;
    private List<Link> urlToImages;
    private List<Tag> tags;
    private CategoryDto category;
    private UserDto user;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
