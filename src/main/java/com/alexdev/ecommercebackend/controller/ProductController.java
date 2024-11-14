package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.CategoryDTO;
import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.model.entity.Product;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.CategoryService;
import com.alexdev.ecommercebackend.service.OptionService;
import com.alexdev.ecommercebackend.service.ProductService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Products", description = "Operations allowed on the product entity")
@RequestMapping("products")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private OptionService optionService;


    @Operation(summary = "Create a new product")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Created Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("product created successfully")
                .data(productService.create(productDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Update a existing product from an ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Updated Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody ProductDTO productDTO) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("product updated successfully")
                .data(productService.update(id, productDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a existing product from an ID (Deleting the product does not delete the option or the category)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Deleted Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        ProductDTO productDTOremoved = productService.delete(id);

        Map<String, Object> dataResponse = new HashMap<>();
        dataResponse.put("sku", productDTOremoved.getSku());
        dataResponse.put("id", productDTOremoved.getId());
        dataResponse.put("name", productDTOremoved.getName());
        dataResponse.put("description", productDTOremoved.getDescription());

        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("product deleted successfully")
                .data(dataResponse)
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Shows a product from an ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Product Retrived Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("product retrieved successfully")
                .data(productService.getProductDTO(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Show all products")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Products Retrived Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO[].class)) }),
    })
    @GetMapping("")
    public ResponseEntity<?> getAllProducts(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Direction.DESC) Pageable pageable) {
        List<ProductDTO> listProductDTO = productService.getProductsDTO(pageable);
        if (listProductDTO.isEmpty()) {
            throw new EmptyException("No products found");
        }
        return new ResponseEntity<>(ListEntityResponse
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


    @Operation(summary = "Add a product to a category (If the category has not been created, it will be created)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category Added to Product Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
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

        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("category added to product successfully")
                .data(productService.addCategory(id, categoryDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Add a product to a option (If the option has not been created, it will be created)")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Option Added to Product Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
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

        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("option added to product successfully")
                .data(productService.addOption(id, optionDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Remove a product from a category")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Category removed from Product Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
    @DeleteMapping("{id}/categories")
    public ResponseEntity<?> removeCategory(@PathVariable int id, @Valid @RequestBody CategoryDTO categoryDTO) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("category removed from product successfully")
                .data(productService.removeCategory(id, categoryService.getCategoryDTOByName(categoryDTO.getName().strip())))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Remove a product from a option")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Option removed from Product Successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = ProductDTO.class)) }),
    })
    @DeleteMapping("{id}/options")
    public ResponseEntity<?> removeOption(@PathVariable int id, @Valid @RequestBody OptionDTO optionDTO) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("option removed from product successfully")
                .data(productService.removeOption(id, optionService.getOptionDTOByName(optionDTO.getName().strip())))
                .build()
                , HttpStatus.OK);
    }

}
