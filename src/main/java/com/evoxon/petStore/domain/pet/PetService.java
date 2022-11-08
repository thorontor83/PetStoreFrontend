package com.evoxon.petStore.domain.pet;

import java.util.List;

public interface PetService {

    public Pet getPetById(Long petId);

    public List<Pet> getPetsByTags(List<String> tagList);

    public List<Pet> getPetsByStatus(PetStatus petStatus);

    public Pet createPet(Pet pet);

    public Pet updatePet(Pet pet);

    public Boolean deletePet(Long petId);

    List<Pet> getAllPets();
}
