package com.developertools.questionoverflow.model;

import lombok.*;

import javax.persistence.MappedSuperclass;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
@MappedSuperclass
public abstract class BaseEntity {
    private LocalDateTime createDate = LocalDateTime.now();
    private LocalDateTime updateDate;
}
