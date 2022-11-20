package com.developertools.questionoverflow.dto.request;

import lombok.Data;

import java.util.List;

@Data
public class CreateCommentRequest {
    private String body;
    private List<String> urlToImages;
    private String questionPublicId;
    private String userMail;
}
