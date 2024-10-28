package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.entity.Product;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class OrderDetailsDTO implements Serializable {

    private int id;

    @NotNull(message = "cannot be null")
    @Positive
    private double price;

    @NotNull(message = "cannot be null")
    @PositiveOrZero
    private int quantity;


    private Product product;

    private Order order;
}
