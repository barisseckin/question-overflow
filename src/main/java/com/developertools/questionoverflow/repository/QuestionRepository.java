package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.Category;
import com.developertools.questionoverflow.model.Question;
import com.developertools.questionoverflow.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface QuestionRepository extends JpaRepository<Question, Long> {
    Optional<Question> findQuestionByPublicId(String publicId);
    List<Question> findQuestionByCategory(Category category);
    List<Question> findQuestionByUser(User user);
}
