package com.developertools.questionoverflow.service;

import com.developertools.questionoverflow.dto.CategoryDto;
import com.developertools.questionoverflow.dto.converter.CategoryDtoConverter;
import com.developertools.questionoverflow.model.Category;
import com.developertools.questionoverflow.repository.CategoryRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class CategoryServiceTest {

    private CategoryRepository categoryRepository;
    private CategoryDtoConverter categoryDtoConverter;
    private CategoryService categoryService;

    @BeforeEach
    public void setUp() {
        categoryRepository = mock(CategoryRepository.class);
        categoryDtoConverter = mock(CategoryDtoConverter.class);

        categoryService = new CategoryService(categoryRepository, categoryDtoConverter);
    }

    @Test
    public void testGetByName_itShouldReturnCategoryDto() {
        String categoryName = "test";
        Category fromDbCategory = new Category(categoryName);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName(categoryName);

        when(categoryRepository.findCategoryByName(categoryName)).thenReturn(Optional.of(fromDbCategory));
        when(categoryDtoConverter.convertCategoryToCategoryDto(fromDbCategory)).thenReturn(categoryDto);

        CategoryDto result = categoryService.getByName(categoryName);

        assertEquals(categoryDto, result);

        verify(categoryRepository).findCategoryByName(categoryName);
    }

    @Test
    public void testDelete() {
        String categoryName = "test";

        Category fromDbCategory = new Category(1L, categoryName);

        when(categoryRepository.findCategoryByName(fromDbCategory.getName())).thenReturn(Optional.of(fromDbCategory));

        categoryService.delete(fromDbCategory.getName());

        verify(categoryRepository).deleteById(fromDbCategory.getId());
    }

    @Test
    public void testGetAll_itShouldReturnCategoryDtoList() {
        Category fromDbCategory = new Category(1L, "test");
        Category fromDbCategory2 = new Category(2L, "test");

        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setName("test");
        CategoryDto categoryDto2 = new CategoryDto();
        categoryDto2.setName("test");

        List<Category> fromDbCategoryList = List.of(fromDbCategory, fromDbCategory2);
        List<CategoryDto> categoryDtoList = List.of(categoryDto, categoryDto2);

        when(categoryRepository.findAll()).thenReturn(fromDbCategoryList);
        when(categoryDtoConverter.convertCategoryListToCategoryDtoList(fromDbCategoryList)).thenReturn(categoryDtoList);

        List<CategoryDto> result = categoryService.getAll();

        assertEquals(categoryDtoList, result);

        verify(categoryRepository).findAll();
    }
}