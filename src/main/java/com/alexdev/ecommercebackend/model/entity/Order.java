package com.alexdev.ecommercebackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.List;

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
    private double ammount;

    @Column(name = "shipping_address")
    private String shippingAddress;

    @Column(name = "order_address")
    private String orderAddress;

    @Column(name = "order_email")
    private String orderEmail;

    @Column(name = "order_status")
    private String orderStatus;

    @Column(name = "sku", unique = true)
    private String sku;


    @ManyToOne
    @JoinColumn(name = "fk_customer")
    @JsonIgnore
    private Customer customer;

    @OneToMany(mappedBy = "order", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<OrderDetails> ordersDetails;

    @OneToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "fk_order_dates")
    @JsonIgnore
    private OrderDates orderDates;
}
