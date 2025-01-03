package com.alexdev.ecommercebackend.model.mapper.impl;

import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.model.entity.OrderDetails;
import com.alexdev.ecommercebackend.model.mapper.OrderDetailsMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDetailsMapperImpl implements OrderDetailsMapper {

    @Override
    public OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO) {
        if (orderDetailsDTO == null) {
            return null;
        }

        return OrderDetails.builder()
                .id(orderDetailsDTO.getId())
                .price(orderDetailsDTO.getPrice())
                .quantity(orderDetailsDTO.getQuantity())
                .product(orderDetailsDTO.getProduct())
                .order(orderDetailsDTO.getOrder())
                .build();
    }

    @Override
    public OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails) {
        if (orderDetails == null) {
            return null;
        }

        return OrderDetailsDTO.builder()
                .id(orderDetails.getId())
                .price(orderDetails.getPrice())
                .quantity(orderDetails.getQuantity())
                .product(orderDetails.getProduct())
                .order(orderDetails.getOrder())
                .build();
    }

    @Override
    public List<OrderDetailsDTO> toOrderDetailsDTOs(List<OrderDetails> ordersDetails) {
        if (ordersDetails == null) {
            return null;
        }
        List<OrderDetailsDTO> orderDetailsDTOS = new ArrayList<>(ordersDetails.size());
        for (OrderDetails orderDetails : ordersDetails) {
            orderDetailsDTOS.add(toOrderDetailsDTO(orderDetails));
        }
        return orderDetailsDTOS;
    }

    @Override
    public List<OrderDetails> toOrderDetailsList(List<OrderDetailsDTO> ordersDetailsDTOList) {
        if (ordersDetailsDTOList == null) {
            return null;
        }
        List<OrderDetails> orderDetailsList = new ArrayList<>(ordersDetailsDTOList.size());
        for (OrderDetailsDTO orderDetailsDTO : ordersDetailsDTOList) {
            orderDetailsList.add(toOrderDetails(orderDetailsDTO));
        }
        return orderDetailsList;
    }

    @Override
    public void updateOrderDetails(OrderDetails orderDetails, OrderDetailsDTO orderDetailsDTO) {
        if (orderDetails == null) {
            return;
        }
        orderDetailsDTO.setId(orderDetails.getId());
        orderDetailsDTO.setPrice(orderDetails.getPrice());
        orderDetailsDTO.setQuantity(orderDetails.getQuantity());
        orderDetailsDTO.setProduct(orderDetails.getProduct());
        orderDetailsDTO.setOrder(orderDetails.getOrder());
    }
}
