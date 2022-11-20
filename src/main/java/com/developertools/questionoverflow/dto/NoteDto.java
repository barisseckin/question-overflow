package com.developertools.questionoverflow.dto;

import com.developertools.questionoverflow.model.Link;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class NoteDto {
    private String title;
    private String description;
    private String publicId;
    private List<Link> urlToImages;
    private String userMail;
    private LocalDateTime createDate;
    private LocalDateTime updateDate;
}
