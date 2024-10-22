package com.alexdev.ecommercebackend.service;


import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;

import java.util.List;

public interface OrderDatesService {

    List<OrderDatesDTO> getOrdersDates();

    OrderDatesDTO create(OrderDatesDTO orderDatesDTO);

    OrderDatesDTO update(int orderDatesId, OrderDatesDTO orderDatesDTO);

    OrderDatesDTO getOrderDates(int OrderDatesDTOId);

    OrderDatesDTO delete(int OrderDatesDTOId);

    boolean existsByid(int id);
}
