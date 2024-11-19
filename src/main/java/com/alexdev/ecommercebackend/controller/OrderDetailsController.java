package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Order Products", description = "Operations allowed on the orderDetails entity")
@RequestMapping("order_details")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;


    @Operation(summary = "Update a product into the current order")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OrderDetailsDTO orderDetailsDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("order details updated successfully")
                .data(orderDetailsService.update(id, orderDetailsDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Shows a product of the order from an ID")
    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("order details retrieved successfully")
                .data(orderDetailsService.getOrderDetails(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Show all product of the order")
    @GetMapping("")
    public ResponseEntity<?> getAllProducts(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<OrderDetailsDTO> listOrderDetailsDTO = orderDetailsService.getOrdersDetails(pageable);
        if (listOrderDetailsDTO.isEmpty()) {
            throw new EmptyException("No orders details found");
        }
        return new ResponseEntity<>(ListEntityResponse
                .builder()
                .message("orders details retrieved successfully")
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(orderDetailsService.count())
                .count(listOrderDetailsDTO.size())
                .data(listOrderDetailsDTO)
                .build()
                , HttpStatus.OK);
    }

}
