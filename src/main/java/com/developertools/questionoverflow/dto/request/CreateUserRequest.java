package com.developertools.questionoverflow.dto.request;

import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Data
public class CreateUserRequest {
    @NotBlank
    private String username;
    @Email
    private String mail;
    @NotBlank
    private String password;
    private String urlToImage;
    private String link;
}
