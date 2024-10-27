package com.alexdev.ecommercebackend.model.mapper;

import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.entity.Category;

import java.util.List;

public interface CategoryMapper {

    Category toCategory(CategoryDTO categoryDTO);

    CategoryDTO toCategoryDto(Category category);

    List<CategoryDTO> toCategoryDtos(List<Category> categories);

    void updateCategory(Category category, CategoryDTO categoryDTO);
}
