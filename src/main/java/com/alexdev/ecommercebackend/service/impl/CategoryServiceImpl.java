package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.entity.Category;
import com.alexdev.ecommercebackend.model.mapper.CategoryMapper;
import com.alexdev.ecommercebackend.repository.CategoryRepository;
import com.alexdev.ecommercebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;

    @Override
    public List<CategoryDTO> getCategories() {
        return categoryMapper.toCategoryDtos(categoryRepository.findAll());
    }

    @Override
    public CategoryDTO getCategory(int id) {
        Category category = categoryRepository.findById(id).get();
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public CategoryDTO save(CategoryDTO categoryDTO) {
        categoryDTO.setId(0);
        categoryDTO.setCreationDate(new Date());
        Category category = categoryMapper.toCategory(categoryDTO);
        return categoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO update(int categoryId, CategoryDTO categoryDTO) {
        Category category = categoryRepository.findById(categoryId).get();
        categoryDTO.setId(categoryId);
        categoryMapper.updateCategory(category, categoryDTO);
        return categoryMapper.toCategoryDto(categoryRepository.save(category));
    }

    @Override
    public CategoryDTO delete(int categoryDTOId) {
        Category category = categoryRepository.findById(categoryDTOId).get();
        CategoryDTO categoryDTO = categoryMapper.toCategoryDto(category);
        categoryRepository.delete(category);
        return categoryDTO;
    }

    @Override
    public boolean existsById(int id) {
        return categoryRepository.existsById(id);
    }
}
