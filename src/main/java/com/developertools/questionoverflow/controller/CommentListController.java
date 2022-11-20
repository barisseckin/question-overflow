package com.developertools.questionoverflow.controller;

import com.developertools.questionoverflow.dto.CommentDto;
import com.developertools.questionoverflow.service.CommentListService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/v1/api/comment/list")
public class CommentListController {

    private final CommentListService commentListService;

    public CommentListController(CommentListService commentListService) {
        this.commentListService = commentListService;
    }

    @GetMapping("/get-by-question-public-id/{questionPublicId}")
    public ResponseEntity<List<CommentDto>> getCommentListByQuestionPublicId(
            @PathVariable(value = "questionPublicId") String questionPublicId) {

        return ResponseEntity
                .ok(commentListService.getCommentListByQuestionPublicId(questionPublicId));
    }

    @GetMapping("/get-by-public-id/{questionPublicId}")
    public ResponseEntity<CommentDto> getByPublicId(
            @PathVariable(value = "questionPublicId") String questionPublicId) {

        return ResponseEntity
                .ok(commentListService.getByPublicId(questionPublicId));
    }

    @GetMapping("/get-by-user-mail/{mail}")
    public ResponseEntity<List<CommentDto>> getByUserMail(@PathVariable(value = "mail") String mail) {
        return ResponseEntity
                .ok(commentListService.getByUserMail(mail));
    }
}
