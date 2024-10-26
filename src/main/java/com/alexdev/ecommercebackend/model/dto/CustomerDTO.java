package com.alexdev.ecommercebackend.model.dto;

import com.alexdev.ecommercebackend.constants.RegexExpresions;
import com.alexdev.ecommercebackend.model.entity.Order;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.*;
import lombok.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@ToString
@Builder
public class CustomerDTO implements Serializable {

    private int id;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String name;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String lastName;

    @Pattern(regexp = RegexExpresions.rgxPhone, message = "incorrect format")
    private String phone;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    @Pattern(regexp = RegexExpresions.rgxEmail, message = "incorrect format")
    private String email;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String billingAddress;

    @NotBlank(message = "cannot be blank")
    @NotNull(message = "cannot be null")
    private String country;

    private Date creationDate;


    private List<Order> orders;

}
