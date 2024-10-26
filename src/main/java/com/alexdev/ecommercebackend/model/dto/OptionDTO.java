package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.model.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class OptionDTO implements Serializable {

    private int id;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String name;

    private Date creationDate;


    private List<Product> products;

}
