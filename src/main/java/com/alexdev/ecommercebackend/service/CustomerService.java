package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import com.alexdev.ecommercebackend.model.entity.Customer;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface CustomerService {

    List<CustomerDTO> getCustomersDTO(Pageable pageable);

    CustomerDTO save(CustomerDTO customerDTO);

    CustomerDTO update(int customerId, CustomerDTO customerDTO);

    CustomerDTO GetCustomerDTO(int id);

    Customer GetCustomer(int id);

    CustomerDTO delete(int customerDTOId);

    boolean existsById(int id);

    int count();
}
