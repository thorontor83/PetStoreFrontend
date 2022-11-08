package com.evoxon.petStore.dto;

import com.evoxon.petStore.domain.pet.Category;
import com.evoxon.petStore.domain.pet.Pet;
import com.evoxon.petStore.domain.pet.PetStatus;
import com.evoxon.petStore.persistence.PetEntity;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class PetDtoTest {

  /*  @Test
    void fromEntityToDomain() {
        //given
        PetEntity petEntity = new PetEntity(1L,"Pitbull",1L,"Dog","medium dangerous male", PetStatus.AVAILABLE);
        Pet petExpected = new Pet(1L,"Pitbull",new Category(1L,"Dog"), List.of("medium","dangerous","male"), PetStatus.AVAILABLE);
        //when
        Pet pet = PetDto.fromEntityToDomain(petEntity);
        //then
        assertThat(pet.getId()).isEqualTo(petExpected.getId());
        assertThat(pet.getPetName()).isEqualTo(petExpected.getPetName());
        assertThat(pet.getCategory().getId()).isEqualTo(petExpected.getCategory().getId());
        assertThat(pet.getCategory().getCategoryName()).isEqualTo(petExpected.getCategory().getCategoryName());
        assertThat(pet.getTags()).isEqualTo(petExpected.getTags());
        assertThat(pet.getPetStatus()).isEqualTo(petExpected.getPetStatus());
    }

    @Test
    void fromDomainToEntity() {
        //given
        Pet pet = new Pet(1L,"Pitbull",new Category(1L,"Dog"), List.of("medium","dangerous","male"), PetStatus.AVAILABLE);
        PetEntity petEntityExpected = new PetEntity(1L,"Pitbull",1L,"Dog","medium dangerous male", PetStatus.AVAILABLE);
        //when
        PetEntity petEntity = PetDto.fromDomainToEntity(pet);
        //then
        assertThat(petEntity.getId()).isEqualTo(petEntityExpected.getId());
        assertThat(petEntity.getPetName()).isEqualTo(petEntityExpected.getPetName());
        assertThat(petEntity.getCategoryId()).isEqualTo(petEntityExpected.getCategoryId());
        assertThat(petEntity.getCategoryName()).isEqualTo(petEntityExpected.getCategoryName());
        assertThat(petEntity.getTags()).isEqualTo(petEntityExpected.getTags());
        assertThat(petEntity.getPetStatus()).isEqualTo(petEntityExpected.getPetStatus());

    }*/
}