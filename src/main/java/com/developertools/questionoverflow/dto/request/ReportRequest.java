package com.developertools.questionoverflow.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class ReportRequest {
    @NotBlank
    private String publicId;
    private String message;
}
