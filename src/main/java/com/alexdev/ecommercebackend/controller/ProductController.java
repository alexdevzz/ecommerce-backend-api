package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.payload.ListMessageResponse;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.CategoryService;
import com.alexdev.ecommercebackend.service.OptionService;
import com.alexdev.ecommercebackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OptionService optionService;


    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("product created successfully")
                .data(productService.create(productDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("product updated successfully")
                .data(productService.update(id, productDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("product deleted successfully")
                .data(productService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("product retrieved successfully")
                .data(productService.getProductDTO(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Direction.DESC) Pageable pageable) {
        List<ProductDTO> listProductDTO = productService.getProductsDTO(pageable);
        if (listProductDTO.isEmpty()) {
            throw new EmptyException("No products found");
        }
        return new ResponseEntity<>(ListMessageResponse
                .builder()
                .message("products retrieved successfully")
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(productService.count())
                .count(listProductDTO.size())
                .data(listProductDTO)
                .build()
                , HttpStatus.OK);
    }


    @PostMapping("{id}/categories")
    public ResponseEntity<?> addCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO categoryDTO) {

        if (! categoryService.existsByNameIgnoreCase(categoryDTO.getName().strip()))
        {
            categoryDTO.setName(categoryDTO.getName().strip());
            categoryDTO = categoryService.create(categoryDTO);
        }
        else
        {
            categoryDTO = categoryService.getCategoryDTOByName(categoryDTO.getName().strip());
        }

        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("category added to product successfully")
                .data(productService.addCategory(id, categoryDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PostMapping("{id}/options")
    public ResponseEntity<?> addOption(@PathVariable int id, @Valid @RequestBody OptionDTO optionDTO) {

        if (! optionService.existsByNameIgnoreCase(optionDTO.getName().strip()))
        {
            optionDTO.setName(optionDTO.getName().strip());
            optionDTO = optionService.create(optionDTO);
        }
        else
        {
            optionDTO = optionService.getOptionDTOByName(optionDTO.getName().strip());
        }

        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("option added to product successfully")
                .data(productService.addOption(id, optionDTO))
                .build()
                , HttpStatus.CREATED);
    }

}
