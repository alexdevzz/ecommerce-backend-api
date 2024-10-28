package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.entity.Category;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CategoryService {

    List<CategoryDTO> getCategoriesDTO(Pageable pageable);

    List<Category> getCategories(Pageable pageable);

    CategoryDTO getCategoryDTO(int id);

    Category getCategory(int id);

    CategoryDTO getCategoryDTOByName(String name);

    CategoryDTO create(CategoryDTO categoryDTO);

    CategoryDTO update(int categoryId, CategoryDTO categoryDTO);

    CategoryDTO delete(int categoryDTOId);

    boolean existsById(int id);

    boolean existsByNameIgnoreCase(String name);

    int count();


}
