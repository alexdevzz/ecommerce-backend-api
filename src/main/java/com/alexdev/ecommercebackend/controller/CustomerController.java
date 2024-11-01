package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.*;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.CustomerService;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import com.alexdev.ecommercebackend.service.OrderService;
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
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;


    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("client created successfully")
                .data(customerService.save(customerDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("client updated successfully")
                .data(customerService.update(id, customerDTO))
                .build()
                , HttpStatus.CREATED);

    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("client deleted successfully")
                .data(customerService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("client retrieved successfully")
                .data(customerService.GetCustomerDTO(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllCustomers(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        List<CustomerDTO> listCustomerDTO = customerService.getCustomersDTO(pageable);
        if (listCustomerDTO.isEmpty()) {
            throw new EmptyException("No customers found");
        }
        return new ResponseEntity<>(ListEntityResponse
                .builder()
                .message("customers retrieved successfully")
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(customerService.count())
                .count(listCustomerDTO.size())
                .data(listCustomerDTO)
                .build()
                , HttpStatus.OK);
    }


    @PostMapping("{id}/orders")
    public ResponseEntity<?> addOrder(@PathVariable int id, @Valid @RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("order created successfully")
                .data(orderService.create(orderDTO, customerService.GetCustomerDTO(id)))
                .build()
                , HttpStatus.CREATED);
    }



}
