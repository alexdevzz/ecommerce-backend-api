package com.alexdev.ecommercebackend.model.mapper;

import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import com.alexdev.ecommercebackend.model.entity.Customer;

import java.util.List;

public interface CustomerMapper {

    Customer toCustomer(CustomerDTO customerDto);

    CustomerDTO toCustomerDto(Customer customer);

    List<CustomerDTO> toCustomerDtos(List<Customer> customers);

    void updateCustomer(Customer customer, CustomerDTO customerDto);
}
