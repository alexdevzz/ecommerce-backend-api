package com.alexdev.ecommercebackend.model.mapper.impl;

import com.alexdev.ecommercebackend.model.dto.OrderDatesDTO;
import com.alexdev.ecommercebackend.model.entity.OrderDates;
import com.alexdev.ecommercebackend.model.mapper.OrderDatesMapper;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderDatesMapperImpl implements OrderDatesMapper {

    @Override
    public OrderDates toOrderDates(OrderDatesDTO orderDatesDTO) {
        if (orderDatesDTO == null) {
            return null;
        }
        return OrderDates.builder()
                .id(orderDatesDTO.getId())
                .creationDate(orderDatesDTO.getCreationDate())
                .billingDate(orderDatesDTO.getBillingDate())
                .warehouseOriginReceptionDate(orderDatesDTO.getWarehouseOriginReceptionDate())
                .orderInContainerDate(orderDatesDTO.getOrderInContainerDate())
                .sentDate(orderDatesDTO.getSentDate())
                .arrivedPortDate(orderDatesDTO.getArrivedPortDate())
                .customClearanceDate(orderDatesDTO.getCustomClearanceDate())
                .warehouseDestinyReceptionDate(orderDatesDTO.getWarehouseDestinyReceptionDate())
                .readyToSendDate(orderDatesDTO.getReadyToSendDate())
                .inDeliveryRouteDate(orderDatesDTO.getInDeliveryRouteDate())
                .orderDeliveredDate(orderDatesDTO.getOrderDeliveredDate())
                .build();
    }

    @Override
    public OrderDatesDTO toOrderDatesDTO(OrderDates orderDates) {
        if (orderDates == null) {
            return null;
        }

        return OrderDatesDTO.builder()
                .id(orderDates.getId())
                .creationDate(orderDates.getCreationDate())
                .billingDate(orderDates.getBillingDate())
                .warehouseOriginReceptionDate(orderDates.getWarehouseOriginReceptionDate())
                .orderInContainerDate(orderDates.getOrderInContainerDate())
                .sentDate(orderDates.getSentDate())
                .arrivedPortDate(orderDates.getArrivedPortDate())
                .customClearanceDate(orderDates.getCustomClearanceDate())
                .warehouseDestinyReceptionDate(orderDates.getWarehouseDestinyReceptionDate())
                .readyToSendDate(orderDates.getReadyToSendDate())
                .inDeliveryRouteDate(orderDates.getInDeliveryRouteDate())
                .orderDeliveredDate(orderDates.getOrderDeliveredDate())
                .build();
    }

    @Override
    public List<OrderDatesDTO> toOrderDatesDTOs(List<OrderDates> ordersDates) {
        if (ordersDates == null) {
            return null;
        }
        List<OrderDatesDTO> orderDatesDTOs = new ArrayList<>(ordersDates.size());
        for (OrderDates orderDates : ordersDates) {
            orderDatesDTOs.add(toOrderDatesDTO(orderDates));
        }
        return orderDatesDTOs;
    }

    @Override
    public void updateOrderDates(OrderDates orderDates, OrderDatesDTO orderDatesDTO) {
        if (orderDates == null) {
            return;
        }
        orderDates.setId(orderDatesDTO.getId());
        orderDates.setCreationDate(orderDatesDTO.getCreationDate());
        orderDates.setBillingDate(orderDatesDTO.getBillingDate());
        orderDates.setWarehouseOriginReceptionDate(orderDatesDTO.getWarehouseOriginReceptionDate());
        orderDates.setOrderInContainerDate(orderDatesDTO.getOrderInContainerDate());
        orderDates.setSentDate(orderDatesDTO.getSentDate());
        orderDates.setArrivedPortDate(orderDatesDTO.getArrivedPortDate());
        orderDates.setCustomClearanceDate(orderDatesDTO.getCustomClearanceDate());
        orderDates.setReadyToSendDate(orderDatesDTO.getReadyToSendDate());
        orderDates.setInDeliveryRouteDate(orderDatesDTO.getInDeliveryRouteDate());
        orderDates.setOrderDeliveredDate(orderDatesDTO.getOrderDeliveredDate());
        orderDates.setWarehouseDestinyReceptionDate(orderDatesDTO.getWarehouseDestinyReceptionDate());

    }
}
