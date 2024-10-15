package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.CategoryDTO;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategories();

    CategoryDTO getCategory(int id);

    CategoryDTO save(CategoryDTO categoryDTO);

    CategoryDTO update(int categoryId, CategoryDTO categoryDTO);

    CategoryDTO delete(int categoryDTOId);

    boolean existsById(int id);
}
