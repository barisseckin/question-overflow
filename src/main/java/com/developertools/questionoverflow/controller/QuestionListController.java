package com.developertools.questionoverflow.controller;

import com.developertools.questionoverflow.dto.QuestionDto;
import com.developertools.questionoverflow.service.QuestionListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/question/list")
public class QuestionListController {

    private final QuestionListService questionListService;

    public QuestionListController(QuestionListService questionListService) {
        this.questionListService = questionListService;
    }

    @GetMapping("/get-by-category-name")
    public ResponseEntity<List<QuestionDto>> getQuestionByCategoryName(@RequestParam String categoryName) {
        return ResponseEntity
                .ok(questionListService.getQuestionByCategoryName(categoryName));
    }

    @GetMapping("/get-by-user-mail")
    public ResponseEntity<List<QuestionDto>> getQuestionByUserMail(@RequestParam String userMail) {
        return ResponseEntity
                .ok(questionListService.getQuestionByUserMail(userMail));
    }

    @GetMapping("/get-by-description")
    public ResponseEntity<List<QuestionDto>> getQuestionByDescription(@RequestParam String description) {
        return ResponseEntity
                .ok(questionListService.getQuestionByDescription(description));
    }

    @GetMapping("/get-by-liked-number")
    public ResponseEntity<List<QuestionDto>> getByLikedNumber(@RequestParam int likedNumber) {
        return ResponseEntity
                .ok(questionListService.getByLikedNumber(likedNumber));
    }

    @GetMapping("/get-general-filter")
    public ResponseEntity<List<QuestionDto>> generalQuestionFilter(@RequestParam String categoryName,
                                                                   @RequestParam String description,
                                                                   @RequestParam int likedNumber,
                                                                   @RequestParam String tags) {
        return ResponseEntity
                .ok(questionListService.generalQuestionFilter(categoryName, description, likedNumber, tags));
    }
}
