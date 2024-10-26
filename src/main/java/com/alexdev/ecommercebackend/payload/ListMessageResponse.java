package com.alexdev.ecommercebackend.payload;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class ListMessageResponse implements Serializable {

    @Builder.Default
    private String message = "";
    @Builder.Default
    private Date timestamp = new Date();
    @Builder.Default
    private String sort = "UNSORTED";
    @Builder.Default
    private int total = -1;
    @Builder.Default
    private int page = -1;
    @Builder.Default
    private int count = -1;
    @Builder.Default
    private Object data = null;

}
