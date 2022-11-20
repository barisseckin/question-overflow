package com.developertools.questionoverflow.repository;

import com.developertools.questionoverflow.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<Category, Long> {
    Optional<Category> findCategoryByName(String name);
    boolean existsByName(String name);
}
