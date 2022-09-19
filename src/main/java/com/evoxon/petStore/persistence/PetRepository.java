package com.evoxon.petStore.persistence;

import com.evoxon.petStore.domain.pet.PetStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PetRepository extends JpaRepository<PetEntity,Long> {


    @Query("SELECT p FROM PetEntity p WHERE p.petStatus = :petStatus")
    Optional<List<PetEntity>> findAllWithStatus (PetStatus petStatus);

}
