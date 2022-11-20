package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.Comment;
import com.developertools.questionoverflow.model.Question;
import com.developertools.questionoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface CommentRepository extends JpaRepository<Comment, Long> {
    Optional<Comment> findCommentByPublicId(String publicId);
    List<Comment> findCommentByQuestion(Question question);
    List<Comment> findCommentByUser(User user);
}
