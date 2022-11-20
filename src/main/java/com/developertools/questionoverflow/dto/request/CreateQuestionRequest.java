package com.developertools.questionoverflow.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateQuestionRequest {
    private String title;
    private String description;
    private List<String> urlToImages;
    private List<String> tags;
    private String categoryName;
    private String userMail;
}
