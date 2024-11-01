package com.alexdev.ecommercebackend.model.mapper;

import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.model.entity.OrderDetails;

import java.util.List;

public interface OrderDetailsMapper {

    OrderDetails toOrderDetails(OrderDetailsDTO orderDetailsDTO);

    OrderDetailsDTO toOrderDetailsDTO(OrderDetails orderDetails);

    List<OrderDetailsDTO> toOrderDetailsDTOs(List<OrderDetails> ordersDetails);

    List<OrderDetails> toOrderDetailsList(List<OrderDetailsDTO> ordersDetailsDTO);

    void updateOrderDetails(OrderDetails orderDetails, OrderDetailsDTO orderDetailsDTO);
}
