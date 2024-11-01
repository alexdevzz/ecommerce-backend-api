package com.alexdev.ecommercebackend.constants;

import lombok.Getter;

@Getter
public enum EnumOrderStatus {

    CREATED("created", 1),
    INVOICED("invoiced", 2),
    RECEIVED_AT_ORIGIN_WAREHOUSE("received at origin warehouse", 3),
    IN_CONTAINER("in container", 4),
    SENT("sent", 5),
    ARRIVED("arrived", 6),
    CLEARED_BY_CUSTOMS("cleared by customs", 7),
    IN_DELIVERY_WAREHOUSE("in delivery warehouse", 8),
    READY_TO_SEND("ready to send", 9),
    IN_DELIVERY_ROUTE("in delivery route", 10),
    DELIVERED("delivered", 11),
    ;

    private final String name;
    private final int order;

    private EnumOrderStatus(String name, int order) {
        this.name = name;
        this.order = order;
    }

}

