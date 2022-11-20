package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
