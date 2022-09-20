package com.evoxon.petStore.controller;

import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.domain.pet.Pet;
import com.evoxon.petStore.domain.pet.PetService;
import com.evoxon.petStore.domain.pet.PetStatus;
import com.evoxon.petStore.domain.pet.PetStatusForm;
import com.evoxon.petStore.jwt.TokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping
public class PetController {

    private final PetService petService;

    public PetController(PetService petService) {this.petService = petService;}

    @GetMapping(path = "/api/v1/pet/{petIdString}")
    public ResponseEntity<Object> getPetById(@PathVariable String petIdString){
        if (petIdString == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet's Id is not valid");
        }
        Long petId = Long.parseLong(petIdString);
        Pet petToGet = petService.getPetById(petId);
        if (petToGet != null){
            return ResponseEntity.status(HttpStatus.OK).body(petToGet);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
    }

    @GetMapping(path = "/api/v1/pet/findByTags")
    public ResponseEntity<Object> getPetsByTags(@RequestBody List<String> tagList) {
        if (tagList == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("List of tags is not valid");
        }
        List<Pet> petList = petService.getPetsByTags(tagList);
        if(petList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pets with required tags");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
    }

    @GetMapping(path = "/api/v1/pet/findByStatus")
    public ResponseEntity<Object> getPetsByStatus(@RequestBody PetStatusForm petStatus) {
        if (petStatus == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet Status is not valid");
        }
        List<Pet> petList = petService.getPetsByStatus(petStatus.getPetStatus());
        if(petList.isEmpty()){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No pets with required status");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petList);
        }
    }

    @PostMapping(path = "/api/v1/pet")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> createPet(@RequestBody Pet pet){
        if (pet == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet is not valid");
        }
        Pet petCreated = petService.createPet(pet);
        if (petCreated == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet was not created");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petCreated);
        }
    }

    @PutMapping(path = "/api/v1/pet")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> updatePet(@RequestBody Pet pet){
        if (pet == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet is not valid");
        }
        Pet petUpdated = petService.updatePet(pet);
        if (petUpdated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(petUpdated);
        }
    }

    @DeleteMapping(path = "/api/v1/pet/{petId}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<Object> deletePet(@PathVariable String petId){
        if (petId == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Pet is not valid");
        }
        Long petIdToDelete = Long.parseLong(petId);
        Boolean deleteIsValid = petService.deletePet(petIdToDelete);
        if (deleteIsValid) {
            return ResponseEntity.status(HttpStatus.OK).body("Pet deleted correctly");
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Pet to delete not found");
        }
    }



}
