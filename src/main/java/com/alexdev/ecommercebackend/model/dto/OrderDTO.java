package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.constants.RegexExpresions;
import com.alexdev.ecommercebackend.model.entity.Customer;
import com.alexdev.ecommercebackend.model.entity.OrderDates;
import com.alexdev.ecommercebackend.model.entity.OrderDetails;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.List;

@Data
@ToString
@Builder
public class OrderDTO implements Serializable {

    private int id;

    @PositiveOrZero(message = "cannot be under 0")
    private int ammount;

    private String shippingAddress;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String orderAddress;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Pattern(regexp = RegexExpresions.rgxEmail, message = "incorrect format")
    private String orderEmail;

    private String orderStatus;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String sku;


    private Customer customer;

    private List<OrderDetails> ordersDetails;

    private OrderDates orderDates;

}
