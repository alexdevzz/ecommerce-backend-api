package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.constants.EnumOrderStatus;
import com.alexdev.ecommercebackend.exceptions.ResponseException;
import com.alexdev.ecommercebackend.model.dto.*;
import com.alexdev.ecommercebackend.model.entity.OrderDetails;
import com.alexdev.ecommercebackend.model.mapper.OrderDetailsMapper;
import com.alexdev.ecommercebackend.model.mapper.OrderMapper;
import com.alexdev.ecommercebackend.model.mapper.ProductMapper;
import com.alexdev.ecommercebackend.repository.OrderDetailsRepository;
import com.alexdev.ecommercebackend.service.OrderDetailsService;
import com.alexdev.ecommercebackend.service.OrderService;
import com.alexdev.ecommercebackend.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OrderDetailsServiceImpl implements OrderDetailsService {

    @Autowired
    private OrderDetailsRepository orderDetailsRepository;
    @Autowired
    private OrderDetailsMapper orderDetailsMapper;
    @Autowired
    private ProductService productService;
    @Autowired
    private ProductMapper productMapper;
    @Autowired
    private OrderService orderService;
    @Autowired
    private OrderMapper orderMapper;


    @Override
    public List<OrderDetailsDTO> getOrdersDetails(Pageable pageable) {
        return orderDetailsMapper.toOrderDetailsDTOs(orderDetailsRepository.findAll(pageable).getContent());
    }

    @Override
    public List<OrderDetailsDTO> create(int idOrder, List<AddOrderDetailsDTO> addOrderDetailsDTOList) {

        // evaluate if in this order, may be changes ..
        if (! orderService.getOrderDTO(idOrder).getOrderStatus().equalsIgnoreCase(EnumOrderStatus.CREATED.getName())) {
            throw new ResponseException("order has already been invoiced");
        }

        // detect duplicates ..
        for (int i = 0; i < addOrderDetailsDTOList.size() - 1; i++) {
            for (int j = i + 1; j < addOrderDetailsDTOList.size(); j++) {
                if (addOrderDetailsDTOList.get(i).getProductSku().equalsIgnoreCase(addOrderDetailsDTOList.get(j).getProductSku())) {
                    throw new ResponseException("cannot be duplicates products in the same order (equal sku)");
                }
            }
        }

        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        double ammount = 0;

        for (AddOrderDetailsDTO addOrderDetailsDTO : addOrderDetailsDTOList) {
            ProductDTO queryProductDTO = productService.getProductDTOBySku(addOrderDetailsDTO.getProductSku());

            if (existsByIdOrderAndIdProduct(idOrder, queryProductDTO.getId())) {
                throw new ResponseException("this product(s) already exists in this order");
            }

            if (queryProductDTO.getStock() < addOrderDetailsDTO.getQuantity()) {
                throw new ResponseException("There are products that do not have enough quantity", "quantity");
            }

            productService.subtractStock(queryProductDTO.getId(), addOrderDetailsDTO.getQuantity());

            orderDetailsDTOList.add(OrderDetailsDTO
                    .builder()
                    .id(0)
                    .price(queryProductDTO.getPrice())
                    .quantity(addOrderDetailsDTO.getQuantity())
                    .order(orderMapper.toOrder(orderService.getOrderDTO(idOrder)))
                    .product(productMapper.toProduct(queryProductDTO))
                    .build());
            ammount += queryProductDTO.getPrice() * addOrderDetailsDTO.getQuantity();
        }
        orderService.updateAmmount(idOrder, ammount);
        return orderDetailsMapper.toOrderDetailsDTOs(orderDetailsRepository.saveAll(orderDetailsMapper.toOrderDetailsList(orderDetailsDTOList)));
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
    public List<OrderDetailsDTO> delete(int idOrder, List<String> productsSku) {

        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        StringBuilder skuError = new StringBuilder();
        double restAmmount = 0;

        for (String element : productsSku) {
            try {
                OrderDetailsDTO orderDetailsDTO = orderDetailsMapper.toOrderDetailsDTO(orderDetailsRepository.findOrderDetailsByOrder_IdAndAndProduct_Sku(idOrder, element));
                restAmmount -= orderDetailsDTO.getQuantity() * orderDetailsDTO.getPrice();
                orderDetailsDTOList.add(this.delete(orderDetailsDTO.getId()));
            } catch (NullPointerException e) {
                skuError.append(" '").append(element).append("' ");
            }
        }
        orderService.updateAmmount(idOrder, restAmmount);

        if (!skuError.isEmpty()) {
            throw new ResponseException(skuError.insert(0, "sku code(s):").append("not found in this order. Others product(s) are already removed").toString(), "sku");
        }

        return orderDetailsDTOList;
    }

    @Override
    public boolean existsByid(int id) {
        return orderDetailsRepository.existsById(id);
    }

    @Override
    public boolean existsByIdOrderAndIdProduct(int idOrder, int idProduct) {
        return orderDetailsRepository.existsByOrder_IdAndProduct_Id(idOrder, idProduct);
    }

    @Override
    public int count() {
        return (int) orderDetailsRepository.count();
    }



}

