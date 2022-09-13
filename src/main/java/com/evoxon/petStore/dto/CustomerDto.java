package com.evoxon.petStore.dto;

import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.persistence.CustomerEntity;

public class CustomerDto {

    public static Customer fromEntityToDomain(CustomerEntity customerEntity) {

        return new Customer(customerEntity.getId(), customerEntity.getUsername(), customerEntity.getPassword(),
                customerEntity.getEmail(), customerEntity.getAddress(), customerEntity.getCustomerRole());
    }


    public static CustomerEntity fromDomainToEntity(Customer customer) {

        return new CustomerEntity(customer.getId(), customer.getUsername(), customer.getPassword(),
                customer.getEmail(), customer.getAddress(), customer.getCustomerRole());
    }
}
