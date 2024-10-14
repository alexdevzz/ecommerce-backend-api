package com.alexdev.ecommercebackend.mapper.impl;

import com.alexdev.ecommercebackend.mapper.ClientMapper;
import com.alexdev.ecommercebackend.model.dto.ClientDTO;
import com.alexdev.ecommercebackend.model.entity.Client;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class ClienteMapperImpl implements ClientMapper {

    @Override
    public Client toClient(ClientDTO clientDto) {
        if (clientDto == null) {
            return null;
        }

        return Client.builder()
                .id(clientDto.getId())
                .name(clientDto.getName())
                .lastName(clientDto.getLastName())
                .registerDate(clientDto.getRegisterDate())
                .email(clientDto.getEmail())
                .phone(clientDto.getPhone())
                .address(clientDto.getAddress())
                .email(clientDto.getEmail())
                .build();
    }

    @Override
    public ClientDTO toClientDto(Client client) {

        if (client == null) {
            return null;
        }

        return ClientDTO.builder()
                .id(client.getId())
                .name(client.getName())
                .lastName(client.getLastName())
                .registerDate(client.getRegisterDate())
                .email(client.getEmail())
                .phone(client.getPhone())
                .address(client.getAddress())
                .email(client.getEmail())
                .build();
    }

    @Override
    public List<ClientDTO> toClientDtos(List<Client> clients) {
        if (clients == null) {
            return null;
        }

        List<ClientDTO> clientDTOS = new ArrayList<ClientDTO>(clients.size());
        for (Client client : clients) {
            clientDTOS.add(toClientDto(client));
        }
        return clientDTOS;
    }

    @Override
    public void updateClient(Client client, ClientDTO clientDto) {

        if (clientDto == null) {
            return;
        }

        client.setId(clientDto.getId());
        client.setName(clientDto.getName());
        client.setLastName(clientDto.getLastName());
        client.setRegisterDate(clientDto.getRegisterDate());
        client.setEmail(clientDto.getEmail());
        client.setPhone(clientDto.getPhone());
        client.setAddress(clientDto.getAddress());
        client.setEmail(clientDto.getEmail());

    }
}
