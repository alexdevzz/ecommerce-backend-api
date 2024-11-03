package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.model.entity.Category;
import com.alexdev.ecommercebackend.model.entity.Option;
import com.alexdev.ecommercebackend.model.entity.OrderDetails;
import com.fasterxml.jackson.annotation.JsonGetter;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

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


    private List<Option> options;

    private List<Category> categories;

    private List<OrderDetails> ordersDetails;

}
