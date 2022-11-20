package com.developertools.questionoverflow.controller;

import com.developertools.questionoverflow.dto.CategoryDto;
import com.developertools.questionoverflow.dto.request.CreateCategoryRequest;
import com.developertools.questionoverflow.service.CategoryService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/v1/api/category")
public class CategoryController {

    private final CategoryService categoryService;

    public CategoryController(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    @PostMapping
    public ResponseEntity<CategoryDto> save(@RequestBody @Valid CreateCategoryRequest request) {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(categoryService.save(request));
    }

    @DeleteMapping
    public ResponseEntity<?> delete(@RequestParam String name) {
        categoryService.delete(name);

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/get-by-name")
    public ResponseEntity<CategoryDto> getByName(@RequestParam String name) {
        return ResponseEntity.ok(categoryService.getByName(name));
    }

    @GetMapping
    public ResponseEntity<List<CategoryDto>> getAll() {
        return ResponseEntity.ok(categoryService.getAll());
    }
}
