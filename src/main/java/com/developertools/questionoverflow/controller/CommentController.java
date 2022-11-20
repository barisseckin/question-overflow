package com.developertools.questionoverflow.controller;

import com.developertools.questionoverflow.dto.CommentDto;
import com.developertools.questionoverflow.dto.request.CreateCommentRequest;
import com.developertools.questionoverflow.service.CommentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/v1/api/comment")
public class CommentController {

    private final CommentService commentService;

    public CommentController(CommentService commentService) {
        this.commentService = commentService;
    }

    @PostMapping
    public ResponseEntity<CommentDto> save(@RequestBody @Valid CreateCommentRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(commentService.save(request));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String publicId) {
        commentService.delete(publicId);
        return ResponseEntity.noContent().build();
    }

    @PatchMapping("/like")
    public ResponseEntity<CommentDto> likeComment(@RequestParam String publicId) {
        return ResponseEntity
                .ok(commentService.likeComment(publicId));
    }

    @PatchMapping("/dislike")
    public ResponseEntity<CommentDto> dislikeComment(@RequestParam String publicId) {
        return ResponseEntity
                .ok(commentService.dislikeComment(publicId));
    }

    @PatchMapping("/approve")
    public ResponseEntity<CommentDto> approveComment(@RequestParam String publicId) {
        return ResponseEntity
                .ok(commentService.approveComment(publicId));
    }
}
