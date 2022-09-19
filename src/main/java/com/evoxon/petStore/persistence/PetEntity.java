package com.evoxon.petStore.persistence;

import com.evoxon.petStore.domain.pet.Category;
import com.evoxon.petStore.domain.pet.PetStatus;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "pets")
public class PetEntity {
    @Id
    @SequenceGenerator(
            name="pet_sequence",
            sequenceName = "pet_sequence",
            initialValue = 0,
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "pet_sequence"
    )
    private Long id;
    private String petName;
    private Long categoryId;
    private String categoryName;
    private String tags;
    @Enumerated
    private PetStatus petStatus;

    public PetEntity() {
    }

    public PetEntity(String petName, Long categoryId, String categoryName, String tags, PetStatus petStatus) {
        this.petName = petName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
        this.tags = tags;
        this.petStatus = petStatus;
    }

    public PetEntity(Long id, String petName, Long categoryId, String categoryName, String tags, PetStatus petStatus) {
        this.id = id;
        this.petName = petName;
        this.categoryId = categoryId;
        this.categoryName = categoryName;
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

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public PetStatus getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(PetStatus petStatus) {
        this.petStatus = petStatus;
    }
}
