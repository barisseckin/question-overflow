package com.developertools.questionoverflow.dto.request;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class CreateCategoryRequest {
    @NotBlank
    String name;
}
