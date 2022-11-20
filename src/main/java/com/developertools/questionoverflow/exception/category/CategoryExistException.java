package com.developertools.questionoverflow.exception.category;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CategoryExistException extends RuntimeException{

    public CategoryExistException(String message) {
        super(message);
    }
}
