package com.alexdev.ecommercebackend.controller;

import com.alexdev.ecommercebackend.exceptions.GlobalExceptionHandler;
import com.alexdev.ecommercebackend.model.dto.ClientDTO;
import com.alexdev.ecommercebackend.payload.MessageResponse;
import com.alexdev.ecommercebackend.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping("/api/v1")
public class ClientController {

    @Autowired
    private ClientService clientService;

    // TODO validar si esta ya creado el cliente ...
    @PostMapping("clients")
    public ResponseEntity<?> create(@Valid @RequestBody ClientDTO clientDTO) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client created successfully")
                .data(clientService.save(clientDTO))
                .build()
                , HttpStatus.CREATED);
    }

    @PutMapping("clients/{id}")
    public ResponseEntity<?> update(@Valid @RequestBody ClientDTO clientDTO, @PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client updated successfully")
                .data(clientService.update(id, clientDTO))
                .build()
                , HttpStatus.CREATED);

    }

    @DeleteMapping("clients/{id}")
    public ResponseEntity<?> delete(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client deleted successfully")
                .data(clientService.delete(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("clients/{id}")
    public ResponseEntity<?> showByID(@PathVariable int id) {
        return new ResponseEntity<>(MessageResponse.builder()
                .message("client info obtained successfully")
                .data(clientService.GetClient(id))
                .build()
                , HttpStatus.OK);
    }

    @GetMapping("clients")
    public ResponseEntity<?> showAll() {
        List<ClientDTO> listClientDTO = clientService.GetClients();
        return new ResponseEntity<>(MessageResponse.builder()
                .message("clients info obtained successfully")
                .count(listClientDTO.size())
                .data(listClientDTO)
                .build()
                , HttpStatus.OK);
    }
}
