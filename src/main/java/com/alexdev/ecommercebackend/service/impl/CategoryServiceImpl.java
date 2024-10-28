package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.entity.Category;
import com.alexdev.ecommercebackend.model.mapper.CategoryMapper;
import com.alexdev.ecommercebackend.repository.CategoryRepository;
import com.alexdev.ecommercebackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private CategoryMapper categoryMapper;


    @Override
    public List<CategoryDTO> getCategoriesDTO(Pageable pageable) {
        return categoryMapper.toCategoryDtos(categoryRepository.findAll(pageable).getContent());
    }

    @Override
    public List<Category> getCategories(Pageable pageable) {
        return categoryRepository.findAll(pageable).getContent();
    }

    @Override
    public CategoryDTO getCategoryDTO(int id) {
        Category category = categoryRepository.findById(id).get();
        return categoryMapper.toCategoryDto(category);
    }

    @Override
    public Category getCategory(int id) {
        return categoryRepository.findById(id).get();
    }

    @Override
    public CategoryDTO getCategoryDTOByName(String name) {
        return categoryMapper.toCategoryDto(categoryRepository.getByNameIgnoreCase(name));
    }

    @Override
    public CategoryDTO create(CategoryDTO categoryDTO) {
        categoryDTO.setId(0);
        categoryDTO.setCreationDate(new Date());
        categoryDTO.setProducts(new ArrayList<>());
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

    @Override
    public boolean existsByNameIgnoreCase(String name) {
        return categoryRepository.existsByNameIgnoreCase(name.strip());
    }

    @Override
    public int count() {
        return (int) categoryRepository.count();
    }

}
