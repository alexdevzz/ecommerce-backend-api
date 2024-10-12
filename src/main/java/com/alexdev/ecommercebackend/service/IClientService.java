package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.ClientDto;
import com.alexdev.ecommercebackend.model.entity.Client;

import java.util.List;

public interface IClientService {

    List<Client> findAll();

    Client save(ClientDto clientDto);

    Client findById(int id);

    void delete(Client client);

    boolean existsById(int id);
}
