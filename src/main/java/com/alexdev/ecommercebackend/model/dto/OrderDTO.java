package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.constants.RegexExpresions;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class OrderDTO implements Serializable {

    private int id;

    @NotNull(message = "cannot be null")
    @Positive(message = "cannot be 0 or under")
    private int ammount;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String shippingAddress;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String orderAddress;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Pattern(regexp = RegexExpresions.rgxEmail, message = "incorrect format")
    private String orderEmail;
}
