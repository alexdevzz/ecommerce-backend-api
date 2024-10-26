package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.mapper.CustomerMapper;
import com.alexdev.ecommercebackend.repository.CustomerRepository;
import com.alexdev.ecommercebackend.model.dto.CustomerDTO;
import com.alexdev.ecommercebackend.model.entity.Customer;
import com.alexdev.ecommercebackend.service.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private CustomerMapper customerMapper;


    @Override
    public List<CustomerDTO> getCustomers(Pageable pageable) {
        return customerMapper.toCustomerDtos(customerRepository.findAll(pageable).getContent());
    }

    @Override
    public CustomerDTO save(CustomerDTO customerDTO) {
        customerDTO.setId(0);
        customerDTO.setCreationDate(new Date());
        customerDTO.setOrders(new ArrayList<>());
        Customer customer = customerMapper.toCustomer(customerDTO);
        return customerMapper.toCustomerDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO update(int customerId, CustomerDTO customerDTO) {
        Customer customer = customerRepository.findById(customerId).get();
        customerDTO.setId(customerId);
        customerDTO.setCreationDate(customer.getCreationDate());
        customerMapper.updateCustomer(customer, customerDTO);
        return customerMapper.toCustomerDto(customerRepository.save(customer));
    }

    @Override
    public CustomerDTO GetCustomer(int id) {
        Customer customer = customerRepository.findById(id).get();
        return customerMapper.toCustomerDto(customer);
    }

    @Override
    public CustomerDTO delete(int customerDTOId) {
        Customer customer = customerRepository.findById(customerDTOId).get();
        CustomerDTO customerDTO = customerMapper.toCustomerDto(customer);
        customerRepository.delete(customer);
        return customerDTO;
    }

    @Override
    public boolean existsById(int id) {
        return customerRepository.existsById(id);
    }

    @Override
    public int count() {
        return (int) customerRepository.count();
    }


}
