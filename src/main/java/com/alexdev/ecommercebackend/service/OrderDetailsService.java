package com.alexdev.ecommercebackend.service;


import com.alexdev.ecommercebackend.model.dto.AddOrderDetailsDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Map;

public interface OrderDetailsService {

    List<OrderDetailsDTO> getOrdersDetails(Pageable pageable);

    List<OrderDetailsDTO> create(int idOrder, List<AddOrderDetailsDTO> addOrderDetailsDTOList);

    OrderDetailsDTO update(int id, OrderDetailsDTO orderDetailsDTO);

    OrderDetailsDTO getOrderDetails(int idd);

    OrderDetailsDTO delete(int id);

    List<OrderDetailsDTO> delete(int idOrder, List<String> productsSku);

    boolean existsByid(int id);

    boolean existsByIdOrderAndIdProduct(int idOrder, int idProduct);

    int count();

}
