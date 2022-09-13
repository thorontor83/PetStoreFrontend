package com.evoxon.petStore.dto;

import com.evoxon.petStore.domain.order.Order;
import com.evoxon.petStore.persistence.OrderEntity;

public class OrderDto {

    public static Order fromEntityToDomain(OrderEntity orderEntity) {
        return new Order(orderEntity.getId(), orderEntity.getPetId(), orderEntity.getQuantity(),
                orderEntity.getShipDate(), orderEntity.getOrderStatus(), orderEntity.isComplete());
    }

    public static OrderEntity fromDomainToEntity(Order order) {
        return new OrderEntity(order.getId(), order.getPetId(), order.getQuantity(),
                order.getShipDate(), order.getOrderStatus(), order.isComplete());
    }
}
