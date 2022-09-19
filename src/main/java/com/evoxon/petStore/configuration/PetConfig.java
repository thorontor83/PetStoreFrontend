package com.evoxon.petStore.configuration;

import com.evoxon.petStore.domain.customer.CustomerRole;
import com.evoxon.petStore.domain.pet.PetStatus;
import com.evoxon.petStore.persistence.CustomerEntity;
import com.evoxon.petStore.persistence.CustomerRepository;
import com.evoxon.petStore.persistence.PetEntity;
import com.evoxon.petStore.persistence.PetRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class PetConfig {

    private final PetRepository petRepository;

    public PetConfig(PetRepository petRepository) {
        this.petRepository = petRepository;
    }

    @Bean
    CommandLineRunner commandLineRunner2(PetRepository petRepository){
        return args -> {
            PetEntity petEntity1 = new PetEntity("German Shepperd", 1L, "Dog","Medium Friendly Smart", PetStatus.AVAILABLE);
            PetEntity petEntity2 = new PetEntity("Siamese", 2L, "Cat","Small Friendly", PetStatus.AVAILABLE);
            PetEntity petEntity3 = new PetEntity("Boxer", 1L, "Dog","Medium Aggressive", PetStatus.PENDING);
            PetEntity petEntity4 = new PetEntity("Pony", 3L, "Horse","Large Friendly Rideable", PetStatus.AVAILABLE);
            PetEntity petEntity5 = new PetEntity("Sphynx", 2L, "Cat","Medium Aggressive Furless", PetStatus.SOLD);
            PetEntity petEntity6 = new PetEntity("Hummingbird", 4L, "Bird","Small Delicate", PetStatus.PENDING);

            petRepository.saveAllAndFlush(List.of(petEntity1,petEntity2,petEntity3,petEntity4,petEntity5,petEntity6));
        };
    }

}
