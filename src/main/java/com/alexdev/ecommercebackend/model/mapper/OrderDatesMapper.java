package com.alexdev.ecommercebackend.model.mapper;

import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.entity.OrderDates;

import java.util.List;

public interface OrderDatesMapper {

    OrderDates toOrderDates(OrderDatesDTO orderDatesDTO);

    OrderDatesDTO toOrderDatesDTO(OrderDates orderDates);

    List<OrderDatesDTO> toOrderDatesDTOs(List<OrderDates> ordersDates);

    void updateOrderDates(OrderDates orderDates, OrderDatesDTO orderDatesDTO);
}
