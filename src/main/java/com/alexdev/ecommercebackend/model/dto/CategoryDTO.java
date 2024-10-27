package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.model.entity.Product;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class CategoryDTO implements Serializable {

    private int id;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String name;

    private String description;

    private Date creationDate;

    private List<Product> products;

}
