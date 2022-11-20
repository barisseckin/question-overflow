package com.developertools.questionoverflow.dto.converter;

import com.developertools.questionoverflow.dto.CategoryDto;
import com.developertools.questionoverflow.model.Category;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class CategoryDtoConverter {

    public CategoryDto convertCategoryToCategoryDto(Category from) {
        return new CategoryDto(
                from.getName(),
                from.getCreateDate(),
                from.getUpdateDate()
        );
    }

    public List<CategoryDto> convertCategoryListToCategoryDtoList(List<Category> from) {
        return from.stream().map(this::convertCategoryToCategoryDto).collect(Collectors.toList());
    }
}
