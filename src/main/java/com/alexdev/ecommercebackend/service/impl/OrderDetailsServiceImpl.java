package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.model.entity.OrderDetails;
import com.alexdev.ecommercebackend.model.mapper.OrderDetailsMapper;
import com.alexdev.ecommercebackend.repository.OrderDetailsRepository;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;


    @Override
    public List<OrderDetailsDTO> getOrdersDetails() {
        return orderDetailsMapper.toOrderDetailsDTOs(orderDetailsRepository.findAll());
    }

    @Override
    public OrderDetailsDTO save(OrderDetailsDTO orderDetailsDTO) {
        orderDetailsDTO.setId(0);
        OrderDetails orderDetails = orderDetailsMapper.toOrderDetails(orderDetailsDTO);
        return orderDetailsMapper.toOrderDetailsDTO(orderDetailsRepository.save(orderDetails));
    }

    @Override
    public OrderDetailsDTO update(int id, OrderDetailsDTO orderDetailsDTO) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id).get();
        orderDetailsDTO.setId(id);
        orderDetailsMapper.updateOrderDetails(orderDetails, orderDetailsDTO);
        return orderDetailsMapper.toOrderDetailsDTO(orderDetailsRepository.save(orderDetails));
    }

    @Override
    public OrderDetailsDTO getOrderDetails(int idd) {
        OrderDetails orderDetails = orderDetailsRepository.findById(idd).get();
        return orderDetailsMapper.toOrderDetailsDTO(orderDetails);
    }

    @Override
    public OrderDetailsDTO delete(int id) {
        OrderDetails orderDetails = orderDetailsRepository.findById(id).get();
        OrderDetailsDTO orderDetailsDTO = orderDetailsMapper.toOrderDetailsDTO(orderDetails);
        orderDetailsRepository.delete(orderDetails);
        return orderDetailsDTO;
    }

    @Override
    public boolean existsByid(int id) {
        return orderDetailsRepository.existsById(id);
    }
}
