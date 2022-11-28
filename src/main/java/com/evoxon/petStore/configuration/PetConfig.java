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
            PetEntity petEntity1 = new PetEntity("German Shepherd","photo1.png", 1L, "Dog","Medium Friendly Smart", PetStatus.AVAILABLE);
            PetEntity petEntity2 = new PetEntity("Siamese","photo2.png", 2L, "Cat","Small Friendly", PetStatus.AVAILABLE);
            PetEntity petEntity3 = new PetEntity("Boxer","photo3.png", 1L, "Dog","Medium Aggressive", PetStatus.PENDING);
            PetEntity petEntity4 = new PetEntity("Pony","photo4.png", 3L, "Horse","Large Friendly Rideable", PetStatus.AVAILABLE);
            PetEntity petEntity5 = new PetEntity("Sphynx","photo5.png", 2L, "Cat","Medium Aggressive Furless", PetStatus.SOLD);
            PetEntity petEntity6 = new PetEntity("Hummingbird","photo6.png", 4L, "Bird","Small Delicate", PetStatus.PENDING);
            PetEntity petEntity7 = new PetEntity("Bulldog","photo7.png",1L,"Dog","Medium Friendly Lazy", PetStatus.AVAILABLE);
            PetEntity petEntity8 = new PetEntity("Chihuahua","photo8.png",1L,"Dog","Small Aggressive", PetStatus.AVAILABLE);
            PetEntity petEntity9 = new PetEntity("Stray","photo9.png",2L,"Cat","Medium Independent", PetStatus.PENDING);
            PetEntity petEntity10 = new PetEntity("Sparrow","photo10.png",4L,"Bird","Small Singer", PetStatus.AVAILABLE);
            PetEntity petEntity11 = new PetEntity("Mastiff","photo11.png",1L,"Dog","Large Friendly", PetStatus.SOLD);
            PetEntity petEntity12 = new PetEntity("Corgie","photo12.png",1L,"Dog","Small Friendly Smart", PetStatus.AVAILABLE);

            petRepository.saveAllAndFlush(List.of(petEntity1,petEntity2,petEntity3,petEntity4,petEntity5,petEntity6,petEntity7,petEntity8,
                    petEntity9,petEntity10,petEntity11,petEntity12));
        };
    }

}
