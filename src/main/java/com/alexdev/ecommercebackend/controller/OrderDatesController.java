package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.OrderDatesService;
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
@Tag(name = "Order Dates", description = "Operations allowed on the orderDates entity")
@RequestMapping("order_dates")
public class OrderDatesController {

    @Autowired
    private OrderDatesService orderDatesService;


    @Operation(summary = "Shows a order dates from an ID")
    @GetMapping("{id}")
    public ResponseEntity<?> getOrderDates(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("order dates retrieved successfully")
                .data(orderDatesService.getOrderDates(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Show all order dates")
    @GetMapping("")
    public ResponseEntity<?> getAllOrdersDates(@PageableDefault(page = 0, size = 10, sort = "creationDate", direction = Sort.Direction.DESC) Pageable pageable) {
        List<OrderDatesDTO> listOrderDatesDTO = orderDatesService.getOrdersDates(pageable);
        if (listOrderDatesDTO.isEmpty()) {
            throw new EmptyException("No orders dates found");
        }
        return new ResponseEntity<>(ListEntityResponse
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
