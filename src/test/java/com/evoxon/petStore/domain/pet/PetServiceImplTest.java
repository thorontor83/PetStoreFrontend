package com.evoxon.petStore.domain.pet;

import com.evoxon.petStore.domain.customer.CustomerServiceImpl;
import com.evoxon.petStore.dto.PetDto;
import com.evoxon.petStore.persistence.CustomerRepository;
import com.evoxon.petStore.persistence.PetEntity;
import com.evoxon.petStore.persistence.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.client.ExpectedCount.times;

@ExtendWith(MockitoExtension.class)
class PetServiceImplTest {

    @InjectMocks
    private PetServiceImpl petService;

    @Mock
    private PetRepository petRepository;

  /*  @Test
    void shouldGetPetById() {
        //given
        Pet pet = new Pet(0L, "Chihuahua",new Category(0L,"Dog"), List.of("Small","Aggresive"),PetStatus.AVAILABLE );
        when(petRepository.findById(0L)).thenReturn(Optional.of(PetDto.fromDomainToEntity(pet)));
        //when
        Pet petToCheck = petService.getPetById(pet.getId());
        //then
        assertThat(petToCheck).usingRecursiveComparison().isEqualTo(pet);
    }

    @Test
    void shouldNotGetPetById() {
        //given
        Pet pet = new Pet(0L, "Chihuahua",new Category(0L,"Dog"), List.of("Small","Aggresive"),PetStatus.AVAILABLE );
        when(petRepository.findById(0L)).thenReturn(Optional.empty());
        //when
        Pet petToCheck = petService.getPetById(pet.getId());
        //then
        assertThat(petToCheck).isNull();
    }

    @Test
    void createPet() {
        //given
        Pet pet = new Pet(0L, "Chihuahua",new Category(0L,"Dog"), List.of("Small","Aggresive"),PetStatus.AVAILABLE );
        PetEntity petEntity = PetDto.fromDomainToEntity(pet);
        when(petRepository.save(Mockito.any(PetEntity.class))).thenReturn(petEntity);
        //when
        Pet petToCheck = petService.createPet(pet);
        //then
        assertThat(petToCheck).usingRecursiveComparison().isEqualTo(pet);
    }

    @Test
    void getPetsByTags() {
        //given
        List<String> tagList = List.of("Medium","Rideable");
        PetEntity petEntity1 = new PetEntity(0L,"German Shepperd", 1L, "Dog","Medium Friendly Smart", PetStatus.AVAILABLE);
        PetEntity petEntity2 = new PetEntity(1L,"Siamese", 2L, "Cat","Small Friendly", PetStatus.AVAILABLE);
        PetEntity petEntity3 = new PetEntity(2L,"Boxer", 1L, "Dog","Medium Aggressive", PetStatus.PENDING);
        PetEntity petEntity4 = new PetEntity(3L,"Pony", 3L, "Horse","Large Friendly Rideable", PetStatus.AVAILABLE);
        List<PetEntity> petEntityList = List.of(petEntity1,petEntity2,petEntity3,petEntity4);
        when(petRepository.findAll()).thenReturn(petEntityList);
        //when
        List<Pet> petListToCheck = petService.getPetsByTags(tagList);
        //then
        assertThat(petListToCheck.size()).isEqualTo(3);
        assertThat(petListToCheck.get(0)).usingRecursiveComparison().isEqualTo(PetDto.fromEntityToDomain(petEntity1));
        assertThat(petListToCheck.get(1)).usingRecursiveComparison().isEqualTo(PetDto.fromEntityToDomain(petEntity3));
        assertThat(petListToCheck.get(2)).usingRecursiveComparison().isEqualTo(PetDto.fromEntityToDomain(petEntity4));
    }

    @Test
    void getPetsByStatus() {
        //given
        PetEntity petEntity1 = new PetEntity(0L,"German Shepperd", 1L, "Dog","Medium Friendly Smart", PetStatus.AVAILABLE);
        PetEntity petEntity2 = new PetEntity(1L,"Siamese", 2L, "Cat","Small Friendly", PetStatus.SOLD);
        PetEntity petEntity3 = new PetEntity(2L,"Boxer", 1L, "Dog","Medium Aggressive", PetStatus.PENDING);
        PetEntity petEntity4 = new PetEntity(3L,"Pony", 3L, "Horse","Large Friendly Rideable", PetStatus.AVAILABLE);
        List<PetEntity> petEntityList = List.of(petEntity1,petEntity4);
        when(petRepository.findAllWithStatus(PetStatus.AVAILABLE)).thenReturn(Optional.of(petEntityList));
        //when
        List<Pet> petListToCheck = petService.getPetsByStatus(PetStatus.AVAILABLE);
        //then
        assertThat(petListToCheck.size()).isEqualTo(2);
        assertThat(petListToCheck.get(0)).usingRecursiveComparison().isEqualTo(PetDto.fromEntityToDomain(petEntity1));
        assertThat(petListToCheck.get(1)).usingRecursiveComparison().isEqualTo(PetDto.fromEntityToDomain(petEntity4));
    }

    @Test
    void shouldUpdatePet() {
        //given
        Pet pet = new Pet(0L, "Chihuahua",new Category(0L,"Dog"), List.of("Small","Aggresive"),PetStatus.AVAILABLE );
        Pet petActualized = new Pet(0L,"Siamese",new Category(1L,"Cat"),List.of("Medium","Friendly"),PetStatus.PENDING);
        when(petRepository.findById(0L)).thenReturn(Optional.of(PetDto.fromDomainToEntity(pet)));
        //when
        Pet petToCheck = petService.updatePet(petActualized);
        //then
        assertThat(petToCheck).usingRecursiveComparison().isEqualTo(petActualized);
    }

    @Test
    void shouldNotUpdatePet() {
        //given
        Pet pet = new Pet(0L, "Chihuahua",new Category(0L,"Dog"), List.of("Small","Aggresive"),PetStatus.AVAILABLE );
        Pet petActualized = new Pet(0L,"Siamese",new Category(1L,"Cat"),List.of("Medium","Friendly"),PetStatus.PENDING);
        when(petRepository.findById(0L)).thenReturn(Optional.empty());
        //when
        Pet petToCheck = petService.updatePet(petActualized);
        //then
        assertThat(petToCheck).isNull();
    }

    @Test
    void shouldDeletePet() {
        //given
        Pet pet = new Pet(0L, "Chihuahua",new Category(0L,"Dog"), List.of("Small","Aggresive"),PetStatus.AVAILABLE );
        PetEntity petEntity = PetDto.fromDomainToEntity(pet);
        when(petRepository.findById(0L)).thenReturn(Optional.of(petEntity));
        //when
        boolean isDelete = petService.deletePet(pet.getId());
        //then
        assertThat(isDelete).isTrue();
        verify(petRepository,Mockito.times(1)).delete(petEntity);
    }

    @Test
    void shouldNotDeletePet() {
        //given
        Pet pet = new Pet(0L, "Chihuahua",new Category(0L,"Dog"), List.of("Small","Aggresive"),PetStatus.AVAILABLE );
        PetEntity petEntity = PetDto.fromDomainToEntity(pet);
        when(petRepository.findById(0L)).thenReturn(Optional.empty());
        //when
        boolean isDelete = petService.deletePet(pet.getId());
        //then
        assertThat(isDelete).isFalse();
        verify(petRepository,Mockito.times(0)).delete(petEntity);
    }*/

}