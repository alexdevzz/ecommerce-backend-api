package com.alexdev.ecommercebackend.model.dto;

import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class ProductDTO implements Serializable {

    private int id;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String name;

    private String description;

    @NotNull(message = "cannot be null")
    @Positive(message = "cannot be zero or negative")
    private float price;

}
