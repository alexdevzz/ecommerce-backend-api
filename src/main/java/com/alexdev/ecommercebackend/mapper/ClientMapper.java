package com.alexdev.ecommercebackend.mapper;

import com.alexdev.ecommercebackend.model.dto.ClientDTO;
import com.alexdev.ecommercebackend.model.entity.Client;

import java.util.List;

public interface ClientMapper {

    Client toClient(ClientDTO clientDto);

    ClientDTO toClientDto(Client client);

    List<ClientDTO> toClientDtos(List<Client> clients);

    void updateClient(Client client, ClientDTO clientDto);
}
