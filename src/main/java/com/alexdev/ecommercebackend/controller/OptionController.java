package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.OptionService;
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
@Tag(name = "Options", description = "Operations allowed on the option entity")
@RequestMapping("options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @Operation(summary = "Create a new option")
    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody OptionDTO optionDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("option created successfully")
                .data(optionService.create(optionDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Update a new option")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OptionDTO optionDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("option updated successfully")
                .data(optionService.update(id,optionDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a existing option from an ID (Deleting the option does not delete the product)")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("option deleted successfully")
                .data(optionService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Shows a option from an ID")
    @GetMapping("{id}")
    public ResponseEntity<?> getOption(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("option retrived successfully")
                .data(optionService.getOptionDTO(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Show all options")
    @GetMapping("")
    public ResponseEntity<?> getAllOptions(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        List<OptionDTO> listOptionDTO = optionService.getOptionsDTO(pageable);
        if (listOptionDTO.isEmpty()) {
            throw new EmptyException("No options found");
        }
        return new ResponseEntity<>(ListEntityResponse.builder()
                .message("options retrieved successfully")
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(optionService.count())
                .count(listOptionDTO.size())
                .data(listOptionDTO)
                .build()
                , HttpStatus.OK);
    }
}
