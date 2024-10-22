package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.entity.OrderDates;
import com.alexdev.ecommercebackend.model.mapper.OrderDatesMapper;
import com.alexdev.ecommercebackend.repository.OrderDatesRepository;
import com.alexdev.ecommercebackend.service.OrderDatesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class OrderDatesServiceImpl implements OrderDatesService {

    @Autowired
    OrderDatesRepository orderDatesRepository;
    @Autowired
    OrderDatesMapper orderDatesMapper;


    @Override
    public List<OrderDatesDTO> getOrdersDates() {
        return orderDatesMapper.toOrderDatesDTOs(orderDatesRepository.findAll());
    }

    @Override
    public OrderDatesDTO create(OrderDatesDTO orderDatesDTO) {
        orderDatesDTO.setId(0);
        orderDatesDTO.setCreationDate(new Date());
        OrderDates orderDates = orderDatesMapper.toOrderDates(orderDatesDTO);
        return orderDatesMapper.toOrderDatesDTO(orderDatesRepository.save(orderDates));
    }

    @Override
    public OrderDatesDTO update(int orderDatesId, OrderDatesDTO orderDatesDTO) {
        OrderDates orderDates = orderDatesRepository.findById(orderDatesId).get();
        orderDatesDTO.setId(orderDatesId);
        orderDatesMapper.updateOrderDates(orderDates, orderDatesDTO);
        return orderDatesMapper.toOrderDatesDTO(orderDatesRepository.save(orderDates));
    }

    @Override
    public OrderDatesDTO getOrderDates(int OrderDatesDTOId) {
        OrderDates orderDates = orderDatesRepository.findById(OrderDatesDTOId).get();
        return orderDatesMapper.toOrderDatesDTO(orderDates);
    }

    @Override
    public OrderDatesDTO delete(int OrderDatesDTOId) {
        OrderDates orderDates = orderDatesRepository.findById(OrderDatesDTOId).get();
        OrderDatesDTO orderDatesDTO = orderDatesMapper.toOrderDatesDTO(orderDates);
        orderDatesRepository.delete(orderDates);
        return orderDatesDTO;
    }

    @Override
    public boolean existsByid(int id) {
        return orderDatesRepository.existsById(id);
    }
}
