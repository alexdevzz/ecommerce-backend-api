package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.mapper.OrderMapper;
import com.alexdev.ecommercebackend.repository.OrderRepository;
import com.alexdev.ecommercebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<OrderDTO> getOrders() {
        return orderMapper.toOrderDTOs(orderRepository.findAll());
    }

    @Override
    public OrderDTO save(OrderDTO orderDTO) {
        orderDTO.setId(0);
        Order order = orderMapper.toOrder(orderDTO);
        return orderMapper.toOrderDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO update(int orderId, OrderDTO orderDTO) {
        Order order = orderRepository.findById(orderId).get();
        orderDTO.setId(orderId);
        orderMapper.updateOrder(order, orderDTO);
        return orderMapper.toOrderDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO getOrder(int OrderDTOId) {
        Order order = orderRepository.findById(OrderDTOId).get();
        return orderMapper.toOrderDTO(order);
    }

    @Override
    public OrderDTO delete(int OrderDTOId) {
        Order order = orderRepository.findById(OrderDTOId).get();
        OrderDTO orderDTO = orderMapper.toOrderDTO(order);
        orderRepository.delete(order);
        return orderDTO;
    }

    @Override
    public boolean existsByid(int id) {
        return orderRepository.existsById(id);
    }
}
