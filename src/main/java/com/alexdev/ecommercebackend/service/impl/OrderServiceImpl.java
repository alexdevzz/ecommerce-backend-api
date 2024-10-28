package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.constants.OrderStatusEnum;
import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.entity.OrderDates;
import com.alexdev.ecommercebackend.model.mapper.CustomerMapper;
import com.alexdev.ecommercebackend.model.mapper.OrderDatesMapper;
import com.alexdev.ecommercebackend.model.mapper.OrderMapper;
import com.alexdev.ecommercebackend.repository.OrderRepository;
import com.alexdev.ecommercebackend.service.OrderDatesService;
import com.alexdev.ecommercebackend.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private CustomerMapper customerMapper;
    @Autowired
    private OrderDatesService orderDatesService;
    @Autowired
    private OrderDatesMapper orderDatesMapper;


    @Override
    public List<OrderDTO> getOrders(Pageable pageable) {
        return orderMapper.toOrderDTOs(orderRepository.findAll(pageable).getContent());
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO) {
        orderDTO.setId(0);
        orderDTO.setCustomer(null);
        orderDTO.setOrdersDetails(new ArrayList<>());
        orderDTO.setOrderDates(null);
        Order order = orderMapper.toOrder(orderDTO);
        return orderMapper.toOrderDTO(orderRepository.save(order));
    }

    @Override
    public OrderDTO create(OrderDTO orderDTO, CustomerDTO customerDTO) {
        orderDTO.setId(0);
        orderDTO.setAmmount(0);
        orderDTO.setCustomer(customerMapper.toCustomer(customerDTO));
        orderDTO.setShippingAddress(customerDTO.getBillingAddress());
        orderDTO.setOrderStatus(OrderStatusEnum.CREATED.getName());
        orderDTO.setOrderDates(orderDatesMapper.toOrderDates(orderDatesService.create(OrderDatesDTO.builder().build())));

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

    @Override
    public int count() {
        return (int) orderRepository.count();
    }
}
