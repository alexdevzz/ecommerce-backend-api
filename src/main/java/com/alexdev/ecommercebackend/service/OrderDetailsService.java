package com.alexdev.ecommercebackend.service;


import com.alexdev.ecommercebackend.model.dto.InputOrderDetailsDTO;
import com.alexdev.ecommercebackend.model.dto.OrderDetailsDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface OrderDetailsService {

    List<OrderDetailsDTO> getOrdersDetails(Pageable pageable);

    List<OrderDetailsDTO> create(int idOrder, List<InputOrderDetailsDTO> inputOrderDetailsDTOList);

    OrderDetailsDTO update(int id, OrderDetailsDTO orderDetailsDTO);

    OrderDetailsDTO getOrderDetails(int idd);

    OrderDetailsDTO delete(int id);

    boolean existsByid(int id);

    boolean existsByIdOrderAndIdProduct(int idOrder, int idProduct);

    int count();

}
