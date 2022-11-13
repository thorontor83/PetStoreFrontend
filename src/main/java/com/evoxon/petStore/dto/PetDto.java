package com.evoxon.petStore.dto;

import com.evoxon.petStore.domain.pet.Category;
import com.evoxon.petStore.domain.pet.Pet;
import com.evoxon.petStore.persistence.PetEntity;
import org.springframework.data.domain.Page;

import java.util.Arrays;
import java.util.List;
import java.util.function.Function;

public class PetDto {

    public static Pet fromEntityToDomain(PetEntity petEntity) {
        return new Pet(petEntity.getId(), petEntity.getPetName(),petEntity.getImageSrc(), new Category(petEntity.getCategoryId(),
                petEntity.getCategoryName()), convertToList(petEntity.getTags()), petEntity.getPetStatus());
    }

    public static PetEntity fromDomainToEntity(Pet pet) {
        return new PetEntity(pet.getId(), pet.getPetName(),pet.getImageSrc(), pet.getCategory().getId(), pet.getCategory().getCategoryName(),
                convertToConcatenatedString(pet.getTags()), pet.getPetStatus());
    }

    private static List<String> convertToList(String string) {
        return Arrays.asList(string.split(" "));
    }

    private static String convertToConcatenatedString(List<String> stringList) {
        return String.join(" ", stringList);
    }

    public static Page<Pet> fromPageEntityToDomain (Page<PetEntity> pageEntity){
        return pageEntity.map(new Function<PetEntity, Pet>() {
            @Override
            public Pet apply(PetEntity petEntity) {
                return new Pet(petEntity.getId(), petEntity.getPetName(), petEntity.getImageSrc(),
                        new Category(petEntity.getCategoryId(), petEntity.getCategoryName()),
                        convertToList(petEntity.getTags()), petEntity.getPetStatus());
            }
        });
    }
}

