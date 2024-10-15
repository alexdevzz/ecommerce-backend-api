package com.alexdev.ecommercebackend.model.mapper.impl;

import com.alexdev.ecommercebackend.model.mapper.CustomerMapper;
import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import com.alexdev.ecommercebackend.model.entity.Customer;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CustomerMapperImpl implements CustomerMapper {

    @Override
    public Customer toCustomer(CustomerDTO customerDto) {
        if (customerDto == null) {
            return null;
        }

        return Customer.builder()
                .id(customerDto.getId())
                .name(customerDto.getName())
                .lastName(customerDto.getLastName())
                .creationDate(customerDto.getCreationDate())
                .email(customerDto.getEmail())
                .phone(customerDto.getPhone())
                .country(customerDto.getCountry())
                .billingAddress(customerDto.getBillingAddress())
                .build();
    }

    @Override
    public CustomerDTO toCustomerDto(Customer customer) {

        if (customer == null) {
            return null;
        }

        return CustomerDTO.builder()
                .id(customer.getId())
                .name(customer.getName())
                .lastName(customer.getLastName())
                .creationDate(customer.getCreationDate())
                .email(customer.getEmail())
                .phone(customer.getPhone())
                .billingAddress(customer.getBillingAddress())
                .country(customer.getCountry())
                .build();
    }

    @Override
    public List<CustomerDTO> toCustomerDtos(List<Customer> customers) {
        if (customers == null) {
            return null;
        }

        List<CustomerDTO> customerDTOS = new ArrayList<CustomerDTO>(customers.size());
        for (Customer customer : customers) {
            customerDTOS.add(toCustomerDto(customer));
        }
        return customerDTOS;
    }

    @Override
    public void updateCustomer(Customer customer, CustomerDTO customerDto) {

        if (customerDto == null) {
            return;
        }

        customer.setId(customerDto.getId());
        customer.setName(customerDto.getName());
        customer.setLastName(customerDto.getLastName());
        customer.setCreationDate(customerDto.getCreationDate());
        customer.setEmail(customerDto.getEmail());
        customer.setPhone(customerDto.getPhone());
        customer.setBillingAddress( customerDto.getBillingAddress());
        customer.setCountry(customerDto.getCountry());

    }
}
