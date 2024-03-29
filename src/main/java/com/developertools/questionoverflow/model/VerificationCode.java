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
public class VerificationCode {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String userMail;
    private int code;

    public VerificationCode(String userMail, int code) {
        this.userMail = userMail;
        this.code = code;
    }
}
