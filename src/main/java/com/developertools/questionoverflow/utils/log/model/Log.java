package com.developertools.questionoverflow.utils.log.model;

import lombok.*;

import java.time.LocalDateTime;
import java.util.Random;

@Getter
@Setter
@ToString
@NoArgsConstructor
public class Log {
    private Long id = new Random().nextLong();
    private String description;
    private LocalDateTime createDate = LocalDateTime.now();

    public Log(String description) {
        this.description = description;
    }
}
