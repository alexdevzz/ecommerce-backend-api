package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.CustomerService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/customers")
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
    public ResponseEntity<?> getClient(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client retrieved successfully")
                .data(customerService.GetCustomer(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllClients() {
        List<CustomerDTO> listCustomerDTO = customerService.GetCustomers();
        if (listCustomerDTO.isEmpty()) {
            throw new EmptyException("No clients found");
        }
        return new ResponseEntity<>(MessageResponse.builder()
                .message("clients retrieved successfully")
                .count(listCustomerDTO.size())
                .data(listCustomerDTO)
                .build()
                , HttpStatus.OK);
    }
}
