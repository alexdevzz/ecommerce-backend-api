package com.alexdev.ecommercebackend.model.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
@Entity
@Table(name = "orders_dates")
public class OrderDates implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_order_dates")
    private int id;

    @Column(name = "creation_date")
    private Date creationDate;

    @Column(name = "billing_date")
    private Date billingDate;

    @Column(name = "warehouse_origin_reception_date")
    private Date warehouseOriginReceptionDate;

    @Column(name = "order_in_container_date")
    private Date orderInContainerDate;

    @Column(name = "sent_date")
    private Date sentDate;

    @Column(name = "arrived_port_date")
    private Date arrivedPortDate;

    @Column(name = "custom_clearance_date")
    private Date customClearanceDate;

    @Column(name = "warehouse_destiny_reception_date")
    private Date warehouseDestinyReceptionDate;

    @Column(name = "ready_to_send_date")
    private Date readyToSendDate;

    @Column(name = "in_delivery_route_date")
    private Date inDeliveryRouteDate;

    @Column(name = "order_delivered_date")
    private Date orderDeliveredDate;


    @OneToOne(mappedBy = "orderDates")
    @JsonIgnore
    private Order order;
}
