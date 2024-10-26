package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderService {

    List<OrderDTO> getOrders(Pageable pageable);

    OrderDTO save(OrderDTO orderDTO);

    OrderDTO update(int orderId, OrderDTO orderDTO);

    OrderDTO getOrder(int OrderDTOId);

    OrderDTO delete(int OrderDTOId);

    boolean existsByid(int id);

    int count();
}
