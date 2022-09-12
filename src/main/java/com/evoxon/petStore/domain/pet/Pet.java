package com.evoxon.petStore.domain.pet;


import java.util.List;

public class Pet {
    private Long id;
    private String petName;
    private Category category;
    private List<String> tags;
    private PetStatus petStatus;

    public Pet(Long id, String petName, Category category, List<String> tags, PetStatus petStatus) {
        this.id = id;
        this.petName = petName;
        this.category = category;
        this.tags = tags;
        this.petStatus = petStatus;
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

    @Override
    public String toString() {
        return "Pet{" +
                "id=" + id +
                ", petName='" + petName + '\'' +
                ", category=" + category +
                ", tags=" + tags +
                ", petStatus=" + petStatus +
                '}';
    }
}
