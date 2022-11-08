package com.evoxon.petStore.domain.customer;

import com.evoxon.petStore.dto.CustomerDto;
import com.evoxon.petStore.persistence.CustomerEntity;
import com.evoxon.petStore.persistence.CustomerRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.stream.Collectors;

import static org.apache.commons.lang3.ArrayUtils.toArray;

@Service
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepository customerRepository;

    public CustomerServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }


    @Override
    public List<Customer> getAllCustomers() {
        List<CustomerEntity> entityList = customerRepository.findAll();
        if (entityList.isEmpty()) return null;
        return entityList.stream().map(CustomerDto::fromEntityToDomain).collect(Collectors.toList());
    }

    public Customer getCustomerByName(String username) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findByUsername(username);
        return optionalCustomerEntity.map(CustomerDto::fromEntityToDomain).orElse(null);


    }

    public Customer createCustomer(Customer customer) {
        if(customer.getUsername()==null || customer.getPassword()==null || customer.getCustomerRole()==null){
            return null;
        }
        CustomerEntity customerEntity = customerRepository.saveAndFlush(CustomerDto.fromDomainToEntity(customer));
        return CustomerDto.fromEntityToDomain(customerEntity);
    }

    public String deleteCustomer(String username) {
        Optional<CustomerEntity> optionalCustomerEntityToDelete = customerRepository.findByUsername(username);
        if (optionalCustomerEntityToDelete.isPresent()){
            customerRepository.delete(optionalCustomerEntityToDelete.get());
            return "Customer with username: " + username + " deleted successfully";
        }
        else{
            return null;
        }

    }

    public Customer modifyCustomer(Customer customer) {
        Optional<CustomerEntity> optionalCustomerEntity = customerRepository.findById(customer.getId());
        if (optionalCustomerEntity.isPresent()){
            return CustomerDto.fromEntityToDomain(customerRepository.saveAndFlush(CustomerDto.fromDomainToEntity(customer)));
        }
        else{
            return null;
        }
    }

    public List<Customer> createWithList(List<Customer> customerList) {
        return customerList
                .stream()
                .map(CustomerDto::fromDomainToEntity)
                .map(customerRepository::saveAndFlush)
                .map(CustomerDto::fromEntityToDomain)
                .collect(Collectors.toList());
    }

    public Customer[] createWithArray(Customer[] customerArray) {
        return Arrays.stream(customerArray)
                .map(CustomerDto::fromDomainToEntity)
                .map(customerRepository::saveAndFlush)
                .map(CustomerDto::fromEntityToDomain)
                .toArray(Customer[]::new);
    }
}

