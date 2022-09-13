package com.evoxon.petStore.dto;

import com.evoxon.petStore.domain.pet.Category;
import com.evoxon.petStore.domain.pet.Pet;
import com.evoxon.petStore.persistence.PetEntity;

import java.util.Arrays;
import java.util.List;

public class PetDto {

    public static Pet fromEntityToDomain(PetEntity petEntity) {
        return new Pet(petEntity.getId(), petEntity.getPetName(), new Category(petEntity.getCategoryId(),
                petEntity.getCategoryName()), convertToList(petEntity.getTags()), petEntity.getPetStatus());
    }

    public static PetEntity fromDomainToEntity(Pet pet) {
        return new PetEntity(pet.getId(), pet.getPetName(), pet.getCategory().getId(), pet.getCategory().getCategoryName(),
                convertToConcatenatedString(pet.getTags()), pet.getPetStatus());
    }

    private static List<String> convertToList(String string) {
        return Arrays.asList(string.split(" "));
    }

    private static String convertToConcatenatedString(List<String> stringList) {
        return String.join(" ", stringList);
    }
}

