package com.alexdev.ecommercebackend.service;


import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;

import java.util.List;

public interface OrderDetailsService {

    List<OrderDetailsDTO> getOrdersDetails();

    OrderDetailsDTO save(OrderDetailsDTO orderDetailsDTO);

    OrderDetailsDTO update(int id, OrderDetailsDTO orderDetailsDTO);

    OrderDetailsDTO getOrderDetails(int idd);

    OrderDetailsDTO delete(int id);

    boolean existsByid(int id);
}
