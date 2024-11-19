package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.*;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.CustomerService;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import com.alexdev.ecommercebackend.service.OrderService;
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

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Customers", description = "Operations allowed on the customer entity")
@RequestMapping("customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;


    @Operation(summary = "Create a new customer")
    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody CustomerDTO customerDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("client created successfully")
                .data(customerService.save(customerDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Update a new customer")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@Valid @RequestBody CustomerDTO customerDTO, @PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("client updated successfully")
                .data(customerService.update(id, customerDTO))
                .build()
                , HttpStatus.CREATED);

    }

    @Operation(summary = "Delete a existing customer from an ID (Deleting the customer also deletes all related orders)")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        CustomerDTO customerDTOremoved = customerService.delete(id);

        Map<String, Object> dataResponse = new HashMap<>();
        dataResponse.put("id", customerDTOremoved.getId());
        dataResponse.put("name", customerDTOremoved.getName());
        dataResponse.put("lastName", customerDTOremoved.getLastName());
        dataResponse.put("phone", customerDTOremoved.getPhone());
        dataResponse.put("email", customerDTOremoved.getEmail());
        dataResponse.put("country", customerDTOremoved.getCountry());

        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("client deleted successfully")
                .data(dataResponse)
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Shows a customer from an ID")
    @GetMapping("{id}")
    public ResponseEntity<?> getCustomer(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("client retrieved successfully")
                .data(customerService.GetCustomerDTO(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Show all customers")
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


    @Operation(summary = "Add a order to a customer by ID")
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
