package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.constants.EnumOrderStatus;
import com.alexdev.ecommercebackend.exceptions.EmptyException;
import com.alexdev.ecommercebackend.exceptions.ResponseException;
import com.alexdev.ecommercebackend.model.dto.InputOrderDetailsDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import com.alexdev.ecommercebackend.model.dto.ProductDTO;
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
    public List<OrderDetailsDTO> create(int idOrder, List<InputOrderDetailsDTO> inputOrderDetailsDTOList) {

        // evaluate if in this order, may be changes ..
        OrderDTO orderDTO = orderService.getOrderDTO(idOrder);
        if (! orderService.getOrderDTO(idOrder).getOrderStatus().equalsIgnoreCase(EnumOrderStatus.CREATED.getName()))
        {
            throw new ResponseException("order has already been invoiced");
        }

        // detect duplicates ..
        for (int i=0; i<inputOrderDetailsDTOList.size() - 1; i++)
        {
            for (int j=i+1; j<inputOrderDetailsDTOList.size(); j++)
            {
                if (inputOrderDetailsDTOList.get(i).getProductSku().equalsIgnoreCase(inputOrderDetailsDTOList.get(j).getProductSku()))
                {
                    throw new ResponseException("cannot be duplicates products in the same order (equal sku)");
                }
            }
        }

        List<OrderDetailsDTO> orderDetailsDTOList = new ArrayList<>();
        double ammount = 0;
        for (InputOrderDetailsDTO inputOrderDetailsDTO : inputOrderDetailsDTOList)
        {
            ProductDTO queryProductDTO = productService.getProductDTOBySku(inputOrderDetailsDTO.getProductSku());
            if (existsByIdOrderAndIdProduct(idOrder, queryProductDTO.getId()))
            {
                throw new ResponseException("this product(s) already exists in this order");
            }
            orderDetailsDTOList.add(OrderDetailsDTO
                    .builder()
                    .id(0)
                    .price(queryProductDTO.getPrice())
                    .quantity(inputOrderDetailsDTO.getQuantity())
                    .order(orderMapper.toOrder(orderService.getOrderDTO(idOrder)))
                    .product(productMapper.toProduct(queryProductDTO))
                    .build());
            ammount += queryProductDTO.getPrice() * inputOrderDetailsDTO.getQuantity();
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

