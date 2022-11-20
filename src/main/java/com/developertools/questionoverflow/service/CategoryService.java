package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.CategoryDto;
import com.developertools.questionoverflow.dto.converter.CategoryDtoConverter;
import com.developertools.questionoverflow.dto.request.CreateCategoryRequest;
import com.developertools.questionoverflow.exception.category.CategoryExistException;
import com.developertools.questionoverflow.exception.generic.NotFoundException;
import com.developertools.questionoverflow.model.Category;
import com.developertools.questionoverflow.repository.CategoryRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class CategoryService {

    private final CategoryRepository categoryRepository;
    private final CategoryDtoConverter categoryDtoConverter;

    public CategoryService(CategoryRepository categoryRepository,
                           CategoryDtoConverter categoryDtoConverter) {
        this.categoryRepository = categoryRepository;
        this.categoryDtoConverter = categoryDtoConverter;
    }

    public CategoryDto save(CreateCategoryRequest request) {
        Category saved = new Category(
                request.getName()
        );

        if (!categoryRepository.existsByName(saved.getName())) {
            return categoryDtoConverter.convertCategoryToCategoryDto(categoryRepository.save(saved));
        }

        throw new CategoryExistException("category exist, name: " + saved.getName());
    }

    public CategoryDto getByName(String name) {
        return categoryDtoConverter.convertCategoryToCategoryDto(getCategoryByName(name));
    }

    public List<CategoryDto> getAll() {
        return categoryDtoConverter.convertCategoryListToCategoryDtoList(categoryRepository.findAll());
    }

    public void delete(String name) {
        Category category = getCategoryByName(name);

        categoryRepository.deleteById(category.getId());
    }

    protected Category getCategoryByName(String name) {
        return categoryRepository.findCategoryByName(name)
                .orElseThrow(() -> new NotFoundException("category not found, name: " + name));
    }

}
