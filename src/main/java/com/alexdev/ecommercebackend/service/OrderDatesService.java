package com.alexdev.ecommercebackend.service;


import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDatesService {

    List<OrderDatesDTO> getOrdersDates(Pageable pageable);

    OrderDatesDTO create(OrderDatesDTO orderDatesDTO);

    OrderDatesDTO update(int orderDatesId, OrderDatesDTO orderDatesDTO);

    OrderDatesDTO getOrderDates(int OrderDatesDTOId);

    OrderDatesDTO delete(int OrderDatesDTOId);

    boolean existsByid(int id);

    int count();
}
