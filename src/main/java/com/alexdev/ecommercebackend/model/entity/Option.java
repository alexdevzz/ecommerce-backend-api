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
@Table(name = "options")
public class Option implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_option")
    private int id;

    @Column(name = "name", unique = true, nullable = false)
    private String name;

    @Column(name = "creation_date")
    private Date creationDate;


    @ManyToMany(mappedBy = "options")
    @JsonIgnore
    private List<Product> products;


    @PreRemove
    private void preRemove() {
        products.forEach(product -> product.getOptions().remove(this));
    }







}
