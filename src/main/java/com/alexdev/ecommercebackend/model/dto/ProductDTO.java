package com.alexdev.ecommercebackend.model.dto;

import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ProductDTO implements Serializable {

    private int id;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String sku;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String name;

    private String description;

    @NotNull(message = "cannot be null")
    @Positive
    private double price;

    @NotNull(message = "cannot be null")
    @Positive
    private double weight;

    private Date creationDate;

    @NotNull(message = "cannot be null")
    @PositiveOrZero
    private int stock;

}
