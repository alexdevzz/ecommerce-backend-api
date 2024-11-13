package com.alexdev.ecommercebackend.exceptions;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseException extends RuntimeException {

    private String param = null;


    public ResponseException(String message) {
        super(message);
    }

    public ResponseException(String message, String param) {
        super(message);
        this.setParam(param);
    }
}
