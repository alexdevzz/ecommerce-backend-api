package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.entity.Product;
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
    @Positive
    private int quantity;

    @NotNull(message = "cannot be null")
    private Product product;

    @NotNull(message = "cannot be null")
    private Order order;
}
