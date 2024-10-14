package com.alexdev.ecommercebackend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.annotation.ReadOnlyProperty;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "clients")
public class Client implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_client")
    private int id;

    private String name;

    @Column(name = "last_name")
    private String lastName;

    private String phone;

    @Column(name = "email", unique = true)
    private String email;

    private String address;

    @Column(name = "register_date")
    private Date registerDate = new Date();


}
