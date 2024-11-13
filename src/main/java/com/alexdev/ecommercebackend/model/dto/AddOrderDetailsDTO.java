package com.alexdev.ecommercebackend.model.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class AddOrderDetailsDTO implements Serializable {


    @Positive(message = "cannot be cero or under")
    private int quantity;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String productSku;

}
