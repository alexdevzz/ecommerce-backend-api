package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomers(Pageable pageable);

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO update(int customerId, CustomerDTO customerDTO);

    CustomerDTO GetCustomer(int customerDTOId);

    CustomerDTO delete(int customerDTOId);

    boolean existsById(int id);

    int count();
}
