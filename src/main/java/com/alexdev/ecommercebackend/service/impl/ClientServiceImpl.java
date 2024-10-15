package com.alexdev.ecommercebackend.service.impl;

import com.alexdev.ecommercebackend.model.mapper.ClientMapper;
import com.alexdev.ecommercebackend.repository.ClientRepository;
import com.alexdev.ecommercebackend.model.dto.ClientDTO;
import com.alexdev.ecommercebackend.model.entity.Client;
import com.alexdev.ecommercebackend.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository clientRepository;
    @Autowired
    private ClientMapper clientMapper;


    @Override
    public List<ClientDTO> GetClients() {
        return clientMapper.toClientDtos(clientRepository.findAll());
    }

    @Override
    public ClientDTO save(ClientDTO clientDTO) {
        clientDTO.setId(0);
        clientDTO.setRegisterDate(new Date());

        Client client = clientMapper.toClient(clientDTO);
        return clientMapper.toClientDto(clientRepository.save(client));
    }

    @Override
    public ClientDTO update(int clientId, ClientDTO clientDTO) {
        Client client = clientRepository.findById(clientId).get();
        clientDTO.setId(clientId);
        clientDTO.setRegisterDate(client.getRegisterDate());
        clientMapper.updateClient(client, clientDTO);
        return clientMapper.toClientDto(clientRepository.save(client));
    }

    @Override
    public ClientDTO GetClient(int id) {
        Client client = clientRepository.findById(id).get();
        return clientMapper.toClientDto(client);
    }

    @Override
    public ClientDTO delete(int id) {
        Client client = clientRepository.findById(id).get();
        ClientDTO clientDTO = clientMapper.toClientDto(client);
        clientRepository.delete(client);
        return clientDTO;
    }

    @Override
    public boolean existsById(int id) {
        return clientRepository.existsById(id);
    }





}
