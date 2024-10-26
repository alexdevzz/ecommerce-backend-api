package com.alexdev.ecommercebackend.model.mapper.impl;

import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.entity.Category;
import com.alexdev.ecommercebackend.model.mapper.CategoryMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CategoryMapperImpl implements CategoryMapper {


    @Override
    public Category toCategory(CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return null;
        }

        return Category.builder()
                .id(categoryDTO.getId())
                .name(categoryDTO.getName())
                .description(categoryDTO.getDescription())
                .creationDate(categoryDTO.getCreationDate())
                .products(categoryDTO.getProducts())
                .build();
    }

    @Override
    public CategoryDTO toCategoryDto(Category category) {
        if (category == null) {
            return null;
        }

        return CategoryDTO.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .creationDate(category.getCreationDate())
                .products(category.getProducts())
                .build();
    }

    @Override
    public List<CategoryDTO> toCategoryDtos(List<Category> categories) {
        if (categories == null) {
            return null;
        }

        List<CategoryDTO> categoryDTOS = new ArrayList<>(categories.size());
        for (Category category : categories) {
            categoryDTOS.add(toCategoryDto(category));
        }
        return categoryDTOS;
    }

    @Override
    public void updateCategory(Category category, CategoryDTO categoryDTO) {
        if (categoryDTO == null) {
            return;
        }

        category.setId(categoryDTO.getId());
        category.setName(categoryDTO.getName());
        category.setDescription(categoryDTO.getDescription());
        category.setCreationDate(categoryDTO.getCreationDate());
        category.setProducts(categoryDTO.getProducts());
    }
}
