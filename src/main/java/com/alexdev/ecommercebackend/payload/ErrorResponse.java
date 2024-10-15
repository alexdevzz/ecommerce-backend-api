package com.alexdev.ecommercebackend.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.*;

@Data
@ToString
@Builder
public class ErrorResponse implements Serializable {

    @Builder.Default
    private Date timestamp = new Date();
    @Builder.Default
    private int status = 400;
    @Builder.Default
    private String type = "Bad Request";
    @Builder.Default
    private Map<String, String> errors = new HashMap<>();
}
