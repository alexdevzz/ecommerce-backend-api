package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.Serializable;
import java.util.List;

@RestController
@RequestMapping("/api/v1/categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("category created successfully")
                .data(categoryService.save(categoryDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("category updated successfully")
                .data(categoryService.update(id, categoryDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("category deleted successfully")
                .data(categoryService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCategories() {
        List<CategoryDTO> listCategoryDTO = categoryService.getCategories();
        if (listCategoryDTO.isEmpty()) {
            throw new EmptyException("No categories found");
        }
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("categories retrieved successfully")
                .count(listCategoryDTO.size())
                .data(listCategoryDTO)
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCategory(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("category retrieved successfully")
                .data(categoryService.getCategory(id))
                .build()
                , HttpStatus.OK);
    }








}
