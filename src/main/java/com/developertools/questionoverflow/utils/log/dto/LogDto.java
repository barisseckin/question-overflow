package com.developertools.questionoverflow.utils.log.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
public class LogDto {
    private String description;
    private LocalDateTime createDate;
}
