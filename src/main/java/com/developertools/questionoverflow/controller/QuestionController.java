package com.developertools.questionoverflow.controller;

import com.developertools.questionoverflow.dto.QuestionDto;
import com.developertools.questionoverflow.dto.request.CreateQuestionRequest;
import com.developertools.questionoverflow.service.QuestionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/v1/api/question")
public class QuestionController {

    private final QuestionService questionService;

    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    @PostMapping
    public ResponseEntity<QuestionDto> save(@RequestBody CreateQuestionRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(questionService.save(request));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String publicId) {
        questionService.delete(publicId);

        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/like")
    public ResponseEntity<QuestionDto> likeQuestion(@RequestParam String publicId) {
        return ResponseEntity
                .ok(questionService.likeQuestion(publicId));
    }

    @PatchMapping("/dislike")
    public ResponseEntity<QuestionDto> dislikeQuestion(@RequestParam String publicId) {
        return ResponseEntity
                .ok(questionService.dislikeQuestion(publicId));
    }

    @PatchMapping("/update-done")
    public ResponseEntity<QuestionDto> updateIsDoneStatusByPublicId(@RequestParam String publicId,
                                                                    @RequestParam boolean isDone) {
        return ResponseEntity
                .ok(questionService.updateIsDoneStatusByPublicId(publicId, isDone));
    }
}
