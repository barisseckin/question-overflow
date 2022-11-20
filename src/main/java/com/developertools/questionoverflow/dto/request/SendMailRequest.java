package com.developertools.questionoverflow.dto.request;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class SendMailRequest {
    private String title;
    private String body;
    private String to;
}
