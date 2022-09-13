package com.evoxon.petStore.persistence;


import com.evoxon.petStore.domain.order.OrderStatus;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "orders")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long petId;
    private int quantity;
    private Date shipDate;
    @Enumerated
    private OrderStatus orderStatus;
    private boolean complete;

    public OrderEntity() {
    }

    public OrderEntity(Long id, Long petId, int quantity, Date shipDate, OrderStatus orderStatus, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.orderStatus = orderStatus;
        this.complete = complete;
    }

    public OrderEntity(Long petId, int quantity, Date shipDate, OrderStatus orderStatus, boolean complete) {
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.orderStatus = orderStatus;
        this.complete = complete;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPetId() {
        return petId;
    }

    public void setPetId(Long petId) {
        this.petId = petId;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Date getShipDate() {
        return shipDate;
    }

    public void setShipDate(Date shipDate) {
        this.shipDate = shipDate;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public boolean isComplete() {
        return complete;
    }

    public void setComplete(boolean complete) {
        this.complete = complete;
    }
}
