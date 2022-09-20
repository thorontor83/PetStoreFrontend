package com.evoxon.petStore.domain.order;

import java.util.Date;

public class Order {

    private Long id;
    private Long petId;
    private int quantity;
    private Date shipDate;
    private OrderStatus orderStatus;
    private boolean complete;

    public Order(Long id, Long petId, int quantity, Date shipDate, OrderStatus orderStatus, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.quantity = quantity;
        this.shipDate = shipDate;
        this.orderStatus = orderStatus;
        this.complete = complete;
    }

    void approve() throws Exception {
        if(this.orderStatus.equals(OrderStatus.PLACED) || this.orderStatus.equals(OrderStatus.APPROVED)){
            this.orderStatus = OrderStatus.APPROVED;
        }
        else{
            throw new Exception("Shipped orders cannot change Status");
        }

    }

    void deliver() throws Exception {
        if(this.orderStatus.equals(OrderStatus.DELIVERED) || this.orderStatus.equals(OrderStatus.APPROVED)){
            this.orderStatus = OrderStatus.DELIVERED;
        }
        else{
            throw new Exception("Not approved orders cannot be delivered");
        }
    }

    void complete() throws Exception {
        if (this.orderStatus.equals(OrderStatus.DELIVERED)){
            this.complete = true;
        }
        else{
            throw new Exception("Only delivered orders can be approved");
        }
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

    @Override
    public String toString() {
        return "Order{" +
                "id=" + id +
                ", petId=" + petId +
                ", quantity=" + quantity +
                ", shipDate=" + shipDate +
                ", orderStatus=" + orderStatus +
                ", complete=" + complete +
                '}';
    }
}
