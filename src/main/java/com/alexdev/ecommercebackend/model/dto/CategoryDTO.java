package com.alexdev.ecommercebackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class CategoryDTO implements Serializable {

    private int id;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String name;

    private String description;
}
