package com.alexdev.ecommercebackend.model.mapper;

import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.entity.Order;

import java.util.List;

public interface OrderMapper {

    Order toOrder(OrderDTO orderDTO);

    OrderDTO toOrderDTO(Order order);

    List<OrderDTO> toOrderDTOs(List<Order> orders);

    void updateOrder(Order order, OrderDTO orderDTO);
}
