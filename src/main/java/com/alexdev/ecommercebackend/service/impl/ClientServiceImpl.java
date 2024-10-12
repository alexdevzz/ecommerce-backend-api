package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.dao.ClientDao;
import com.alexdev.ecommercebackend.model.dto.ClientDto;
import com.alexdev.ecommercebackend.model.entity.Client;
import com.alexdev.ecommercebackend.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class ClientServiceImpl implements IClientService {

    @Autowired
    private ClientDao clientDao;

    @Override
    public List<Client> findAll() {
        return (List) clientDao.findAll();
    }

    @Transactional
    @Override
    public Client save(ClientDto clientDto) {
        Client client = Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .registerDate(clientDto.getRegisterDate())
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .address(clientDto.getAddress())
                .email(clientDto.getEmail())
                .build();
        return clientDao.save(client);
    }

    @Transactional(readOnly = true)
    @Override
    public Client findById(int id) {
        return clientDao.findById(id).orElse(null);
    }

    @Transactional
    @Override
    public void delete(Client client) {
        clientDao.delete(client);
    }

    @Override
    public boolean existsById(int id) {
        return clientDao.existsById(id);
    }
}
