package com.evoxon.petStore.domain.pet;


import java.util.List;
import java.util.Objects;

public class Pet {
    private Long id;
    private String petName;

    private String imageSrc;
    private Category category;
    private List<String> tags;
    private PetStatus petStatus;

    public Pet() {
    }

    public Pet(Long id, String petName, String imageSrc, Category category, List<String> tags, PetStatus petStatus) {
        this.id = id;
        this.petName = petName;
        this.imageSrc = imageSrc;
        this.category = category;
        this.tags = tags;
        this.petStatus = petStatus;
    }

    public Pet(String petName, String imageSrc, Category category, List<String> tags, PetStatus petStatus) {
        this.petName = petName;
        this.imageSrc = imageSrc;
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

    public String getImageSrc() {
        return imageSrc;
    }

    public void setImageSrc(String imageSrc) {
        this.imageSrc = imageSrc;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Pet)) return false;
        Pet pet = (Pet) o;
        return Objects.equals(id, pet.id) && Objects.equals(petName, pet.petName) && Objects.equals(category.getCategoryName(), pet.category.getCategoryName())
                && Objects.equals(category.getId(), pet.category.getId()) && Objects.equals(tags, pet.tags) && petStatus == pet.petStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, petName, category, tags, petStatus);
    }
}
