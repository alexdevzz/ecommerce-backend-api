package com.alexdev.ecommercebackend.model.entity;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orders")
public class Order implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order")
    private int id;

    @Column(name = "ammount")
    private int ammount;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_email")
    private String orderEmail;


    @ManyToOne
    @JoinColumn(name = "fk_customer")
    private Customer customer;


}
