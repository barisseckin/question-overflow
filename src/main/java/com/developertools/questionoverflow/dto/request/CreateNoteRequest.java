package com.developertools.questionoverflow.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import java.util.List;

@Data
public class CreateNoteRequest {
    private String title;
    @NotBlank
    private String description;
    private List<String> urlToImages;
    private String userMail;
}
