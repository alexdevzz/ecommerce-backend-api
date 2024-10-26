package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.payload.ListMessageResponse;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/v1/order_details")
public class OrderDetailsController {

    @Autowired
    private OrderDetailsService orderDetailsService;

    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody OrderDetailsDTO orderDetailsDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order details created successfully")
                .data(orderDetailsService.save(orderDetailsDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OrderDetailsDTO orderDetailsDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order details updated successfully")
                .data(orderDetailsService.update(id, orderDetailsDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order details deleted successfully")
                .data(orderDetailsService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getProduct(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order details retrieved successfully")
                .data(orderDetailsService.getOrderDetails(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllProducts(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<OrderDetailsDTO> listOrderDetailsDTO = orderDetailsService.getOrdersDetails(pageable);
        if (listOrderDetailsDTO.isEmpty()) {
            throw new EmptyException("No orders details found");
        }
        return new ResponseEntity<>(ListMessageResponse
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
