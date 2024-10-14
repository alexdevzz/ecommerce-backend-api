package com.alexdev.ecommercebackend.service;

import com.alexdev.ecommercebackend.model.dto.ClientDTO;
import com.alexdev.ecommercebackend.model.entity.Client;

import java.util.List;

public interface ClientService {

    List<ClientDTO> GetClients();

    ClientDTO save(ClientDTO clientDTO);

    ClientDTO update(int clientId, ClientDTO clientDTO);

    ClientDTO GetClient(int clientDTOId);

    ClientDTO delete(int clientDTOId);

    boolean existsById(int id);


}
