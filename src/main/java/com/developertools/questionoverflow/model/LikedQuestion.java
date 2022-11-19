package com.developertools.questionoverflow.model;

import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Getter
@Setter
@ToString
@NoArgsConstructor
@Entity
public class LikedQuestion {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String publicIdOfLikedQuestions;

    public LikedQuestion(String publicIdOfLikedQuestions) {
        this.publicIdOfLikedQuestions = publicIdOfLikedQuestions;
    }
}
