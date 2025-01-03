package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.CategoryService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Categories", description = "Operations allowed on the category entity")
@RequestMapping("categories")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;


    @Operation(summary = "Create a new category")
    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("category created successfully")
                .data(categoryService.create(categoryDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Update a new category")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CategoryDTO categoryDTO, @PathVariable int id) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("category updated successfully")
                .data(categoryService.update(id, categoryDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a existing category from an ID (Deleting the category does not delete the product)")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("category deleted successfully")
                .data(categoryService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Show all categories")
    @GetMapping("")
    public ResponseEntity<?> getAllCategories(@PageableDefault(page = 0, size = 10, sort = "name", direction = Sort.Direction.ASC) Pageable pageable) {
        List<CategoryDTO> listCategoryDTO = categoryService.getCategoriesDTO(pageable);
        if (listCategoryDTO.isEmpty()) {
            throw new EmptyException("No categories found");
        }
        return new ResponseEntity<>(ListEntityResponse
                .builder()
                .message("categories retrieved successfully")
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(categoryService.count())
                .count(listCategoryDTO.size())
                .data(listCategoryDTO)
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Shows a category from an ID")
    @GetMapping("{id}")
    public ResponseEntity<?> getCategory(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("category retrieved successfully")
                .data(categoryService.getCategoryDTO(id))
                .build()
                , HttpStatus.OK);
    }








}
