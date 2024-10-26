package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import com.alexdev.ecommercebackend.payload.ListMessageResponse;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.CustomerService;
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
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;


    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client created successfully")
                .data(customerService.save(customerDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client updated successfully")
                .data(customerService.update(id, customerDTO))
                .build()
                , HttpStatus.CREATED);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client deleted successfully")
                .data(customerService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client retrieved successfully")
                .data(customerService.GetCustomer(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCustomers(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        List<CustomerDTO> listCustomerDTO = customerService.getCustomers(pageable);
        if (listCustomerDTO.isEmpty()) {
            throw new EmptyException("No clients found");
        }
        return new ResponseEntity<>(ListMessageResponse
                .builder()
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(customerService.count())
                .count(listCustomerDTO.size())
                .data(listCustomerDTO)
                .build()
                , HttpStatus.OK);
    }
}
