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
@Table(name = "order_details")
public class OrderDetails implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_details")
    private int id;

    @Column(name = "sku", unique = true)
    private String sku;

    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;


    @ManyToOne
    @JoinColumn(name = "fk_product")
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fk_order")
    private Order order;
}
