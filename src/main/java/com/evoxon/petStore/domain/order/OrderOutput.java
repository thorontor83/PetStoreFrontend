package com.evoxon.petStore.domain.order;

import com.evoxon.petStore.domain.pet.Category;
import com.evoxon.petStore.domain.pet.PetStatus;

import java.util.Date;
import java.util.List;

public class OrderOutput {

    private Long id;

    private Long petId;
    private String petName;
    private String imageSrc;
    private Category category;
    private List<String> tags;
    private PetStatus petStatus;
    private int quantity;
    private Date shipDate;
    private OrderStatus orderStatus;
    private boolean complete;

    public OrderOutput() {
    }

    public OrderOutput(Long id, Long petId, String petName, String imageSrc, Category category, List<String> tags,
                       PetStatus petStatus, int quantity, Date shipDate, OrderStatus orderStatus, boolean complete) {
        this.id = id;
        this.petId = petId;
        this.petName = petName;
        this.imageSrc = imageSrc;
        this.category = category;
        this.tags = tags;
        this.petStatus = petStatus;
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

    public String getPetName() {
        return petName;
    }

    public void setPetName(String petName) {
        this.petName = petName;
    }

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public PetStatus getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(PetStatus petStatus) {
        this.petStatus = petStatus;
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
