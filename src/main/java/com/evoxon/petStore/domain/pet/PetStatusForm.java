package com.evoxon.petStore.domain.pet;

public class PetStatusForm {

    private PetStatus petStatus;

    public PetStatusForm(PetStatus petStatus) {
        this.petStatus = petStatus;
    }

    public PetStatusForm() {
    }

    public PetStatus getPetStatus() {
        return petStatus;
    }

    public void setPetStatus(PetStatus petStatus) {
        this.petStatus = petStatus;
    }
}
