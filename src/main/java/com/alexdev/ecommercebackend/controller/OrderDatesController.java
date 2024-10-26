package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import com.alexdev.ecommercebackend.model.dto.ProductDTO;
import com.alexdev.ecommercebackend.payload.ListMessageResponse;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.OrderDatesService;
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
@RequestMapping("order_dates")
public class OrderDatesController {

    @Autowired
    private OrderDatesService orderDatesService;


    @PostMapping("")
    public ResponseEntity<?> create(@Valid @RequestBody OrderDatesDTO orderDatesDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("order dates created successfully")
                .data(orderDatesService.create(orderDatesDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OrderDatesDTO orderDatesDTO) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("order dates updated successfully")
                .data(orderDatesService.update(id, orderDatesDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("order dates deleted successfully")
                .data(orderDatesService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrderDates(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse
                .builder()
                .message("order dates retrieved successfully")
                .data(orderDatesService.getOrderDates(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOrdersDates(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        List<OrderDatesDTO> listOrderDatesDTO = orderDatesService.getOrdersDates(pageable);
        if (listOrderDatesDTO.isEmpty()) {
            throw new EmptyException("No orders dates found");
        }
        return new ResponseEntity<>(ListMessageResponse
                .builder()
                .message("orders dates retrieved successfully")
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(orderDatesService.count())
                .count(listOrderDatesDTO.size())
                .data(listOrderDatesDTO)
                .build()
                , HttpStatus.OK);
    }
}
