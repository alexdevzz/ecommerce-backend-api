package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.constants.EnumOrderStatus;
import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.exceptions.ResponseException;
import com.alexdev.ecommercebackend.model.dto.AddOrderDetailsDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.model.mapper.OrderDatesMapper;
import com.alexdev.ecommercebackend.payload.ListEntityResponse;
import com.alexdev.ecommercebackend.payload.EntityResponse;
import com.alexdev.ecommercebackend.service.OrderDatesService;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import com.alexdev.ecommercebackend.service.OrderService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@Tag(name = "Orders", description = "Operations allowed on the order entity")
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


    @Operation(summary = "Update a new order")
    @PutMapping("{id}")
    public ResponseEntity<?> update(@PathVariable int id, @Valid @RequestBody OrderDTO orderDTO) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("order updated successfully")
                .data(orderService.update(id,orderDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Delete a existing order from an ID (Deleting the order also deletes all orderDetails and orderDates)")
    @DeleteMapping("{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        OrderDTO orderDTOremoved = orderService.delete(id);

        Map<String, Object> dataResponse = new HashMap<>();
        dataResponse.put("id", orderDTOremoved.getId());
        dataResponse.put("ammount", orderDTOremoved.getAmmount());
        dataResponse.put("shippingAddress", orderDTOremoved.getShippingAddress());
        dataResponse.put("orderAddress", orderDTOremoved.getOrderAddress());
        dataResponse.put("orderEmail", orderDTOremoved.getOrderEmail());
        dataResponse.put("orderStatus", orderDTOremoved.getOrderStatus());
        dataResponse.put("sku", orderDTOremoved.getSku());
        dataResponse.put("customer", orderDTOremoved.getCustomer());

        return new ResponseEntity<>(EntityResponse.builder()
                .message("order deleted successfully")
                .data(dataResponse)
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Shows a order from an ID")
    @GetMapping("{id}")
    public ResponseEntity<?> getOrder(@PathVariable int id) {
        return new ResponseEntity<>(EntityResponse.builder()
                .message("order retrived successfully")
                .data(orderService.getOrderDTO(id))
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Show all orders")
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

    @Operation(summary = "Add a product to a order by ID")
    @PostMapping("{id}/products")
    public ResponseEntity<?> addProducts(@PathVariable int id, @Valid @RequestBody List<AddOrderDetailsDTO> addOrderDetailsDTOList) {
        orderDetailsService.create(id, addOrderDetailsDTOList);
        return new ResponseEntity<>(EntityResponse
                .builder()
                .count(addOrderDetailsDTOList.size())
                .message("products added successfully")
                .data(orderService.getOrderDTO(id))
                .build()
                , HttpStatus.CREATED);
    }

    @Operation(summary = "Remove a product to a order by ID")
    @DeleteMapping("{id}/products")
    public ResponseEntity<?> removeProducts(@PathVariable int id, @RequestBody List<String> productsSku) {
        List<OrderDetailsDTO> orderDetailsDTOList = orderDetailsService.delete(id, productsSku);

        List<Map<String, Object>> dataResponse = new ArrayList<>();
        for (OrderDetailsDTO orderDetailsDTO : orderDetailsDTOList) {
            Map<String, Object> data = new HashMap<>();
            data.put("id", orderDetailsDTO.getId());
            data.put("price", orderDetailsDTO.getPrice());
            data.put("quantity", orderDetailsDTO.getQuantity());
            data.put("productSku", orderDetailsDTO.getProduct().getSku());
            data.put("productName", orderDetailsDTO.getProduct().getName());
            data.put("productDescription", orderDetailsDTO.getProduct().getDescription());
            data.put("orderSku", orderDetailsDTO.getOrder().getSku());
            dataResponse.add(data);
        }

        return new ResponseEntity<>(EntityResponse
                .builder()
                .count(-1)
                .message("products removed successfully")
                .data(dataResponse)
                .build()
                , HttpStatus.OK);
    }

    @Operation(summary = "Advance to the next state (after performing this operation, you cannot add or delete products)")
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
