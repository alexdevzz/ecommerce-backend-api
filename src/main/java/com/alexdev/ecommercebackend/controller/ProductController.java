package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.ProductService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/products")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("product created successfully")
                .data(productService.save(productDTO))
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
                .data(productService.getProduct(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts() {
        List<ProductDTO> listProductDTO = productService.getProducts();
        if (listProductDTO.isEmpty()) {
            throw new EmptyException("No products found");
        }
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("products retrieved successfully")
                .count(listProductDTO.size())
                .data(listProductDTO)
                .build()
                , HttpStatus.OK);
    }







}
