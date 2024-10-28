package com.alexdev.ecommercebackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "products")
public class Product implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_product")
    private int id;

    @Column(name = "sku", unique = true)
    private String sku;

    @Column(name = "name")
    private String name;

    @Column(name = "description")
    private String description;

    @Column(name = "price")
    private double price;

    @Column(name = "weight")
    private double weight;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "stock")
    private int stock;


    @ManyToMany
    @JoinTable(
            name = "product_rel_option",
            joinColumns = @JoinColumn(name = "fk_product"),
            inverseJoinColumns = @JoinColumn(name = "fk_option"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"fk_product", "fk_option"})
    )
    @JsonIgnore
    private List<Option> options;

    @ManyToMany
    @JoinTable(
            name = "product_rel_category",
            joinColumns = @JoinColumn(name = "fk_product"),
            inverseJoinColumns = @JoinColumn(name = "fk_category"),
            uniqueConstraints = @UniqueConstraint(columnNames = {"fk_product", "fk_category"})
    )
    @JsonIgnore
    private List<Category> categories;


    @OneToMany(mappedBy = "product")
    private List<OrderDetails> ordersDetails;



















}
