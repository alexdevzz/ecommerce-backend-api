package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.model.entity.Order;
import com.alexdev.ecommercebackend.model.entity.Product;
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
public class InputOrderDetailsDTO implements Serializable {


    @Positive(message = "cannot be cero or under")
    private int quantity;

    @NotNull(message = "cannot be null")
    @NotBlank(message = "cannot be blank")
    private String productSku;

}
