package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.OptionDTO;
import com.alexdev.ecommercebackend.payload.ListMessageResponse;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.OptionService;
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
@RequestMapping("options")
public class OptionController {

    @Autowired
    private OptionService optionService;

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody OptionDTO optionDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("option created successfully")
                .data(optionService.save(optionDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OptionDTO optionDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("option updated successfully")
                .data(optionService.update(id,optionDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("option deleted successfully")
                .data(optionService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOption(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("option retrived successfully")
                .data(optionService.getOption(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOptions(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        List<OptionDTO> listOptionDTO = optionService.getOptions(pageable);
        if (listOptionDTO.isEmpty()) {
            throw new EmptyException("No options found");
        }
        return new ResponseEntity<>(ListMessageResponse.builder()
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
