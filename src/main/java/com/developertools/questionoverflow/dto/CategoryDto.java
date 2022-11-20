package com.developertools.questionoverflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class CategoryDto {
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
