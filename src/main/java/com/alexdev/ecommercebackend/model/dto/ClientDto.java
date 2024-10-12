package com.alexdev.ecommercebackend.model.dto;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ClientDto implements Serializable {

    private int id;
    private String name;
    private String lastName;
    private String phone;
    private String email;
    private String address;
    private Date registerDate;


}
