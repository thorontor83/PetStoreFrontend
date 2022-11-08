package com.evoxon.petStore.configuration;

import com.evoxon.petStore.domain.customer.CustomerRole;
import com.evoxon.petStore.persistence.CustomerEntity;
import com.evoxon.petStore.persistence.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class CustomerConfig {


    @Bean
    CommandLineRunner commandLineRunner1(CustomerRepository customerRepository){
            return args -> {
                CustomerEntity user1 = new CustomerEntity("Julio", "1234","julio@mail","Picadero 18", CustomerRole.USER);
                CustomerEntity user2 = new CustomerEntity("Paco", "1234","paco@mail","Felix 8", CustomerRole.ADMIN);
                customerRepository.saveAllAndFlush(List.of(user1,user2));
            };
        }

}
