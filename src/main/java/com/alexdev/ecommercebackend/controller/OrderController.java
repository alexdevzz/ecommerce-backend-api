package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/orders")
public class OrderController{

    @Autowired
    private OrderService orderService;

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order created successfully")
                .data(orderService.save(orderDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order updated successfully")
                .data(orderService.update(id,orderDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order deleted successfully")
                .data(orderService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order retrived successfully")
                .data(orderService.getOrder(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOrders() {
        List<OrderDTO> listOrderDTO = orderService.getOrders();
        if (listOrderDTO.isEmpty()) {
            throw new EmptyException("No orders found");
        }
        return new ResponseEntity<>(MessageResponse.builder()
                .message("orders retrieved successfully")
                .data(listOrderDTO)
                .count(listOrderDTO.size())
                .build()
                , HttpStatus.OK);
    }
















}
