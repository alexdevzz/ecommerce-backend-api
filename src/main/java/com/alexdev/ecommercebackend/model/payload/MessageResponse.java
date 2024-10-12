package com.alexdev.ecommercebackend.model.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@Builder
public class MessageResponse implements Serializable {

    private String message;
    private Object data;
}
