package com.evoxon.petStore.domain.pet;

import com.evoxon.petStore.dto.PetDto;
import com.evoxon.petStore.persistence.PetEntity;
import com.evoxon.petStore.persistence.PetRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PetServiceImpl implements PetService{

    private final PetRepository petRepository;

    public PetServiceImpl(PetRepository petRepository) {this.petRepository = petRepository;}


    public Pet getPetById(Long petId) {
        Optional<PetEntity> optionalPetEntity = petRepository.findById(petId);
        return optionalPetEntity.map(PetDto::fromEntityToDomain).orElse(null);
    }

    public Pet createPet(Pet pet) {
        return PetDto.fromEntityToDomain((petRepository.saveAndFlush(PetDto.fromDomainToEntity(pet))));
    }

    public List<Pet> getPetsByTags(List<String> tagList) {
        List<PetEntity> listToReturn = new ArrayList<>();
        List<PetEntity> petEntityList = petRepository.findAll();
        for (String tag : tagList){
            for (PetEntity petEntity : petEntityList){
                if (petEntity.getTags().contains(tag) && !listToReturn.contains(petEntity)){
                    listToReturn.add(petEntity);
                }
            }
        }
        return listToReturn.stream().map(PetDto::fromEntityToDomain).collect(Collectors.toList());

    }

    public List<Pet> getPetsByStatus(PetStatus petStatus) {
        Optional<List<PetEntity>> petEntityList = petRepository.findAllWithStatus(petStatus);
        return petEntityList.map(petEntities -> petEntities.stream().map(PetDto::fromEntityToDomain).collect(Collectors.toList())).orElse(Collections.emptyList());
    }

    public Pet updatePet(Pet pet) {
        Optional<PetEntity> optionalPetEntityToUpdate = petRepository.findById(pet.getId());
        if (optionalPetEntityToUpdate.isPresent()){
            petRepository.save(PetDto.fromDomainToEntity(pet));
            return pet;
        }
        else{
            return null;
        }
    }

    public Boolean deletePet(Long petId) {
        Optional<PetEntity> optionalPetEntity = petRepository.findById(petId);
        if(optionalPetEntity.isPresent()){
            petRepository.delete(optionalPetEntity.get());
            return true;
        }
        else{
            return false;
        }
    }
}
