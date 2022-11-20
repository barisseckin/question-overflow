package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.Question;
import org.springframework.data.jpa.repository.JpaRepository;

public interface QuestionRepository extends JpaRepository<Question, Long> {
}
