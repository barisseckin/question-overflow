package com.developertools.questionoverflow.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryDto {
    private String name;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
