package com.alexdev.ecommercebackend.model.dao;

import com.alexdev.ecommercebackend.model.entity.Client;
import org.springframework.data.repository.CrudRepository;

public interface ClientDao extends CrudRepository<Client, Integer> {
}
