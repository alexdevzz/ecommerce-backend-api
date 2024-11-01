package com.alexdev.ecommercebackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedBy;

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



    @Column(name = "price")
    private double price;

    @Column(name = "quantity")
    private int quantity;


    @ManyToOne
    @JoinColumn(name = "fk_product", nullable = false)
    private Product product;

    @ManyToOne
    @JoinColumn(name = "fk_order", nullable = false)
    @JsonIgnore
    private Order order;

}
