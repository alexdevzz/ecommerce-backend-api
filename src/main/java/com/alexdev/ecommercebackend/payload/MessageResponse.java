package com.alexdev.ecommercebackend.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class MessageResponse implements Serializable {

    @Builder.Default
    private Object message = "";
    @Builder.Default
    private Date timestamp = new Date();
}
