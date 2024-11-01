package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.constants.EnumOrderStatus;
import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.exceptions.ResponseException;
import com.alexdev.ecommercebackend.model.dto.InputOrderDetailsDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import com.alexdev.ecommercebackend.model.entity.OrderDates;
import com.alexdev.ecommercebackend.model.mapper.OrderDatesMapper;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.OrderDatesService;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import com.alexdev.ecommercebackend.service.OrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("orders")
public class OrderController{

    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderDetailsService orderDetailsService;
    @Autowired
    private OrderDatesService orderDatesService;
    @Autowired
    private OrderDatesMapper orderDatesMapper;


    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("order updated successfully")
                .data(orderService.update(id,orderDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("order deleted successfully")
                .data(orderService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("order retrived successfully")
                .data(orderService.getOrderDTO(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("")
    public ResponseEntity<?> getAllOrders(@PageableDefault(page = 0, size = 10) Pageable pageable) {
        List<OrderDTO> listOrderDTO = orderService.getOrdersDTO(pageable);
        if (listOrderDTO.isEmpty()) {
            throw new EmptyException("No orders found");
        }
        return new ResponseEntity<>(ListEntityResponse
                .builder()
                .message("orders retrieved successfully")
                .sort(pageable.getSort().toString())
                .page(pageable.getPageNumber())
                .total(orderService.count())
                .count(listOrderDTO.size())
                .data(listOrderDTO)
                .build()
                , HttpStatus.OK);
    }

    @PostMapping("{id}/products")
    public ResponseEntity<?> addProducts(@PathVariable int id,@Valid @RequestBody List<InputOrderDetailsDTO> inputOrderDetailsDTOList) {

        return new ResponseEntity<>(EntityResponse
                .builder()
                .count(inputOrderDetailsDTOList.size())
                .message("products added successfully")
                .data(orderDetailsService.create(id, inputOrderDetailsDTOList))
                .build()
                , HttpStatus.CREATED);
    }

    @PostMapping("{id}/next_status")
    public ResponseEntity<?> nextStatus(@PathVariable int id) {

        OrderDTO orderDTO = orderService.getOrderDTO(id);

        for (int i=0; i<EnumOrderStatus.values().length; i++) {
            if (EnumOrderStatus.values()[i].getName().equalsIgnoreCase(orderDTO.getOrderStatus())) {
                try {
                    orderDTO.setOrderStatus(EnumOrderStatus.values()[i+1].getName());
                    orderDTO.setOrderDates(orderDatesMapper.toOrderDates(orderDatesService.setNextDate(orderDatesMapper.toOrderDatesDTO(orderDTO.getOrderDates()))));
                    break;
                } catch (ArrayIndexOutOfBoundsException ex) {
                    throw new ResponseException("order status already concluid");
                }
            }
        }

        return new ResponseEntity<>(EntityResponse
                .builder()
                .message("order status updated successfully")
                .data(orderService.update(id,orderDTO))
                .build()
                , HttpStatus.CREATED);
    }














}
