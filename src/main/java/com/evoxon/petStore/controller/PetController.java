package com.evoxon.petStore.controller;

import com.evoxon.petStore.domain.pet.Pet;
import com.evoxon.petStore.domain.pet.PetServiceImpl;
import com.evoxon.petStore.domain.pet.PetStatusForm;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PetController {

    private final PetServiceImpl petServiceImpl;

    public PetController(PetServiceImpl petServiceImpl) {this.petServiceImpl = petServiceImpl;}

    @GetMapping(path = "/api/v1/pet/{petIdString}", produces = "application/json")
    public ResponseEntity<Object> getPetById(@PathVariable String petIdString){
        if (petIdString == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet's Id is not valid");
        }
        Long petId = Long.parseLong(petIdString);
        Pet petToGet = petServiceImpl.getPetById(petId);
        if (petToGet != null){
            return ResponseEntity.status(HttpStatus.OK).body(petToGet);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
    }

    @GetMapping(path = "/api/v1/pet/findByTags", produces = "application/json")
    public ResponseEntity<Object> getPetsByTags(@RequestBody List<String> tagList) {
        if (tagList == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("List of tags is not valid");
        }
        List<Pet> petList = petServiceImpl.getPetsByTags(tagList);
        if(petList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pets with required tags");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
    }

    @GetMapping(path = "/api/v1/pet/findByStatus", produces = "application/json")
    public ResponseEntity<Object> getPetsByStatus(@RequestBody PetStatusForm petStatus) {
        if (petStatus == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet Status is not valid");
        }
        List<Pet> petList = petServiceImpl.getPetsByStatus(petStatus.getPetStatus());
        if(petList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pets with required status");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
    }

    @PostMapping(path = "/api/v1/pet", produces = "application/json")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createPet(@RequestBody Pet pet){
        if (pet == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet is not valid");
        }
        Pet petCreated = petServiceImpl.createPet(pet);
        if (petCreated == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet was not created");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petCreated);
        }
    }

    @PutMapping(path = "/api/v1/pet", produces = "application/json")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updatePet(@RequestBody Pet pet){
        if (pet == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet is not valid");
        }
        Pet petUpdated = petServiceImpl.updatePet(pet);
        if (petUpdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petUpdated);
        }
    }

    @DeleteMapping(path = "/api/v1/pet/{petId}", produces = "application/json")
    //@PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deletePet(@PathVariable String petId){
        if (petId == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet is not valid");
        }
        Long petIdToDelete = Long.parseLong(petId);
        Boolean deleteIsValid = petServiceImpl.deletePet(petIdToDelete);
        if (deleteIsValid) {
            return ResponseEntity.status(HttpStatus.OK).body("Pet deleted correctly");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet to delete not found");
        }
    }



}
