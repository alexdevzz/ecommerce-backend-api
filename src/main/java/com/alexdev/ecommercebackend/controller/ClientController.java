package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.model.dto.ClientDto;
import com.alexdev.ecommercebackend.model.entity.Client;
import com.alexdev.ecommercebackend.model.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.IClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private IClientService clientService;

    @PostMapping("client")
    public ResponseEntity<?> create(@RequestBody ClientDto clientDto) {
        Client clientSave = null;
        try
        {
            clientSave = clientService.save(clientDto);

            return new ResponseEntity<>(MessageResponse.builder()
                    .message("client created successfully")
                    .data(ClientDto.builder()
                            .id(clientSave.getId())
                            .name(clientSave.getName())
                            .lastName(clientSave.getLastName())
                            .registerDate(clientSave.getRegisterDate())
                            .email(clientSave.getEmail())
                            .phone(clientSave.getPhone())
                            .address(clientSave.getAddress())
                            .email(clientSave.getEmail())
                            .build())
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
        catch (DataAccessException exDt)
        {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .data(null)
                    .build()
                    , HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("client/{id}")
    public ResponseEntity<?> update(@RequestBody ClientDto clientDto, @PathVariable int id) {
        Client clientUpdate = null;
        try
        {
            if(clientService.existsById(id))
            {
                clientDto.setId(id);
                clientUpdate = clientService.save(clientDto);
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("client updated successfully")
                        .data(ClientDto.builder()
                                .id(clientUpdate.getId())
                                .name(clientUpdate.getName())
                                .lastName(clientUpdate.getLastName())
                                .registerDate(clientUpdate.getRegisterDate())
                                .email(clientUpdate.getEmail())
                                .phone(clientUpdate.getPhone())
                                .address(clientUpdate.getAddress())
                                .email(clientUpdate.getEmail())
                                .build())
                        .build()
                        , HttpStatus.CREATED);
            }
            else
            {
                return new ResponseEntity<>(MessageResponse.builder()
                        .message("client not found")
                        .data(null)
                        .build()
                        , HttpStatus.NOT_FOUND);
            }
        }
        catch (DataAccessException exDt)
        {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .data(null)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }
    }

    @DeleteMapping("client/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        try
        {
            Client clientToDelete = clientService.findById(id);
            clientService.delete(clientToDelete);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("client deleted successfully")
                    .data(null)
                    .build()
                    , HttpStatus.NO_CONTENT);
        }
        catch (DataAccessException exDt)
        {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message(exDt.getMessage())
                    .data(null)
                    .build()
                    , HttpStatus.METHOD_NOT_ALLOWED);
        }

    }

    @GetMapping("client/{id}")
    public ResponseEntity<?> showByID(@PathVariable int id) {
        try
        {
            Client clientGet = clientService.findById(id);
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("client info obtained successfully")
                    .data(ClientDto.builder()
                            .id(clientGet.getId())
                            .name(clientGet.getName())
                            .lastName(clientGet.getLastName())
                            .registerDate(clientGet.getRegisterDate())
                            .email(clientGet.getEmail())
                            .phone(clientGet.getPhone())
                            .address(clientGet.getAddress())
                            .email(clientGet.getEmail())
                            .build())
                    .build()
                    , HttpStatus.OK);
        }
        catch (NullPointerException exDt)
        {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("the client does not exist")
                    .data(null)
                    .build()
                    , HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("clients")
    public ResponseEntity<?> showAll() {
        List<Client> listClients = clientService.findAll();
        if(listClients == null || listClients.isEmpty())
        {
            return new ResponseEntity<>(MessageResponse.builder()
                    .message("no records in the database")
                    .data(null)
                    .build()
                    , HttpStatus.OK);
        }
        return new ResponseEntity<>(MessageResponse.builder()
                .message("clients info obtained successfully")
                .data(listClients)
                .build()
                , HttpStatus.OK);
    }

}
