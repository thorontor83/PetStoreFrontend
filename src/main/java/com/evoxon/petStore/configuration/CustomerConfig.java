package com.evoxon.petStore.configuration;

import com.evoxon.petStore.domain.customer.CustomerRole;
import com.evoxon.petStore.persistence.CustomerEntity;
import com.evoxon.petStore.persistence.CustomerRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Configuration
public class CustomerConfig {

    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public CustomerConfig(BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    @Bean
    CommandLineRunner commandLineRunner1(CustomerRepository customerRepository){
            return args -> {
                CustomerEntity user1 = new CustomerEntity("Julio", bCryptPasswordEncoder.encode("1234"),"julio@mail","Picadero 18", CustomerRole.USER);
                CustomerEntity user2 = new CustomerEntity("Paco", bCryptPasswordEncoder.encode("1234"),"paco@mail","Felix 8", CustomerRole.ADMIN);
                customerRepository.saveAllAndFlush(List.of(user1,user2));
            };
        }

}
