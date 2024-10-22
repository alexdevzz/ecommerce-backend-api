package com.alexdev.ecommercebackend.model.dto;

import lombok.Builder;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
import java.util.Date;

@Data
@ToString
@Builder
public class OrderDatesDTO implements Serializable {

    private int id;

    private Date creationDate;

    private Date billingDate;

    private Date warehouseOriginReceptionDate;

    private Date orderInContainerDate;

    private Date sentDate;

    private Date arrivedPortDate;

    private Date customClearanceDate;

    private Date warehouseDestinyReceptionDate;

    private Date readyToSendDate;

    private Date inDeliveryRouteDate;

    private Date orderDeliveredDate;

}
