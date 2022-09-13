package com.evoxon.petStore.persistence;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CustomerRepository extends JpaRepository<CustomerEntity,Long> {

    @Query("SELECT c FROM CustomerEntity c WHERE c.username = :username")
    Optional<CustomerEntity> findByUsername (String username);

}
