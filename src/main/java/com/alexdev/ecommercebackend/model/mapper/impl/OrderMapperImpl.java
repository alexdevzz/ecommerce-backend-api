package com.alexdev.ecommercebackend.model.mapper.impl;

import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.mapper.OrderMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderMapperImpl implements OrderMapper {

    @Override
    public Order toOrder(OrderDTO orderDTO) {
        if (orderDTO == null) {
            return null;
        }

        return Order.builder()
                .id(orderDTO.getId())
                .ammount(orderDTO.getAmmount())
                .shippingAddress(orderDTO.getShippingAddress())
                .orderAddress(orderDTO.getOrderAddress())
                .orderEmail(orderDTO.getOrderEmail())
                .ordersDetails(orderDTO.getOrdersDetails())
                .customer(orderDTO.getCustomer())
                .orderDates(orderDTO.getOrderDates())
                .build();
    }

    @Override
    public OrderDTO toOrderDTO(Order order) {
        if (order == null) {
            return null;
        }

        return OrderDTO.builder()
                .id(order.getId())
                .ammount(order.getAmmount())
                .shippingAddress(order.getShippingAddress())
                .orderAddress(order.getOrderAddress())
                .orderEmail(order.getOrderEmail())
                .ordersDetails(order.getOrdersDetails())
                .customer(order.getCustomer())
                .orderDates(order.getOrderDates())
                .build();
    }

    @Override
    public List<OrderDTO> toOrderDTOs(List<Order> orders) {
        if (orders == null) {
            return null;
        }

        List<OrderDTO> orderDTOs = new ArrayList<>(orders.size());
        for (Order order : orders) {
            orderDTOs.add(toOrderDTO(order));
        }
        return orderDTOs;
    }

    @Override
    public void updateOrder(Order order, OrderDTO orderDTO) {
        if (orderDTO == null) {
            return;
        }
        order.setId(orderDTO.getId());
        order.setAmmount(orderDTO.getAmmount());
        order.setShippingAddress(orderDTO.getShippingAddress());
        order.setOrderAddress(orderDTO.getOrderAddress());
        order.setOrderEmail(orderDTO.getOrderEmail());
        order.setOrdersDetails(orderDTO.getOrdersDetails());
        order.setCustomer(orderDTO.getCustomer());
        order.setOrderDates(orderDTO.getOrderDates());
    }
}
