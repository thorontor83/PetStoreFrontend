package com.evoxon.petStore.dto;

import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.persistence.CustomerEntity;

public class CustomerDto {

    public static Customer fromEntityToDomain (CustomerEntity customerEntity){
        return null;
    }

    public static CustomerEntity fromDomainToEntity (Customer customer){
        return null;
    }
}
