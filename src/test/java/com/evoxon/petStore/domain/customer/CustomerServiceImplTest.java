package com.evoxon.petStore.domain.customer;

import com.evoxon.petStore.dto.CustomerDto;
import com.evoxon.petStore.persistence.CustomerEntity;
import com.evoxon.petStore.persistence.CustomerRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static com.fasterxml.jackson.databind.type.LogicalType.Collection;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class CustomerServiceImplTest {

    @InjectMocks
    private CustomerServiceImpl customerService;

    @Mock
    private CustomerRepository customerRepository;

    @Test
    void shouldLoadUserByUsername() {
        //given
        String username = "Julio";
        CustomerEntity customerEntity = new CustomerEntity(0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        when(customerRepository.findByUsername("Julio")).thenReturn(Optional.of(customerEntity));
        //when
        UserDetails userDetails = customerService.loadUserByUsername(username);
        //then
        assertThat(userDetails.getUsername()).isEqualTo("Julio");
        assertThat(userDetails.getPassword()).isEqualTo("1234");
        assertThat(userDetails.getAuthorities()).toString().equals("USER");
    }

    @Test
    void shouldNotLoadUserByUsername() {
        //given
        String username = "Julio";
        CustomerEntity customerEntity = new CustomerEntity(0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        when(customerRepository.findByUsername("Julio")).thenReturn(Optional.empty());
        //when
        assertThatThrownBy(() -> customerService.loadUserByUsername(username)).isInstanceOf(UsernameNotFoundException.class);
        //then
        verify(customerRepository,times(1)).findByUsername("Julio");
    }
    @Test
    void shouldGetCustomerByName() {
        //given
        CustomerEntity customerEntity = new CustomerEntity(0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        when(customerRepository.findByUsername("Julio")).thenReturn(Optional.of(customerEntity));
        //when
        Customer customerToCheck = customerService.getCustomerByName("Julio");
        //then
        assertThat(customerToCheck).usingRecursiveComparison().isEqualTo(CustomerDto.fromEntityToDomain(customerEntity));
    }

    @Test
    void shouldNotGetCustomerByName() {
        //given
        CustomerEntity customerEntity = new CustomerEntity(0L, "Julio", "1234", "julio@mail.com", "Felix 7", CustomerRole.USER);
        when(customerRepository.findByUsername("Julio")).thenReturn(Optional.empty());
        //when
        Customer customerToCheck = customerService.getCustomerByName("Julio");
        //then
        assertThat(customerToCheck).isEqualTo(null);
    }

    @Test
    void shouldCreateCustomer() {
        //given
        Customer customer = new Customer( "Julio", "1234", "julio@mail.com", "Felix 7", CustomerRole.USER);
        when(customerRepository.saveAndFlush(any())).thenReturn(CustomerDto.fromDomainToEntity(customer));
        //when
        Customer customerToCheck = customerService.createCustomer(customer);
        //then
        assertThat(customerToCheck).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    void shouldNotCreateCustomer() {
        //given
        Customer customer = new Customer( null, null, null, null, null);
        //when
        Customer customerToCheck = customerService.createCustomer(customer);
        //then
        assertThat(customerToCheck).isNull();
        verify(customerRepository,times(0)).saveAndFlush(any());
    }

    @Test
    void shouldDeleteCustomer() {
        //given
        String username = "Julio";
        CustomerEntity customerEntity = new CustomerEntity(0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        when(customerRepository.findByUsername("Julio")).thenReturn(Optional.of(customerEntity));
        //when
        String response = customerService.deleteCustomer(username);
        //then
        verify(customerRepository,times(1)).delete(customerEntity);
        assertThat(response).isEqualTo("Customer with username: Julio deleted successfully");
    }

    @Test
    void shouldNotDeleteCustomer() {
        //given
        String username = "Julio";
        CustomerEntity customerEntity = new CustomerEntity(0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        when(customerRepository.findByUsername("Julio")).thenReturn(Optional.empty());
        //when
        String response = customerService.deleteCustomer(username);
        //then
        verify(customerRepository,times(0)).delete(customerEntity);
        assertThat(response).isNull();
    }

    @Test
    void shouldModifyCustomer() {
        //given
        Customer customer = new Customer (0L,"Paco","5678","paco@mail.com","Tierno Galvan 4",CustomerRole.ADMIN);
        CustomerEntity customerEntity = new CustomerEntity(0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        when(customerRepository.findById(0L)).thenReturn(Optional.of(customerEntity));
        when(customerRepository.saveAndFlush(any())).thenReturn(CustomerDto.fromDomainToEntity(customer));
        //when
        Customer customerToCheck = customerService.modifyCustomer(customer);
        //then
        assertThat(customerToCheck).usingRecursiveComparison().isEqualTo(customer);
    }

    @Test
    void shouldNotModifyCustomer() {
        //given
        Customer customer = new Customer (0L,"Paco","5678","paco@mail.com","Tierno Galvan 4",CustomerRole.ADMIN);
        CustomerEntity customerEntity = new CustomerEntity(0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        when(customerRepository.findById(0L)).thenReturn(Optional.empty());
        //when(customerRepository.saveAndFlush(any())).thenReturn(CustomerDto.fromDomainToEntity(customer));
        //when
        Customer customerToCheck = customerService.modifyCustomer(customer);
        //then
        assertThat(customerToCheck).isNull();

    }

    @Test
    void createWithList() {
        //given
        Customer customer1 = new Customer (0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        CustomerEntity customerEntity1 = CustomerDto.fromDomainToEntity(customer1);
        Customer customer2 = new Customer (0L,"Paco","5678","paco@mail.com","Tierno Galvan 4",CustomerRole.ADMIN);
        CustomerEntity customerEntity2 = CustomerDto.fromDomainToEntity(customer2);
        Customer customer3 = new Customer (0L,"Juana","3456","juana@mail.com","Sacrificio 10",CustomerRole.USER);
        CustomerEntity customerEntity3 = CustomerDto.fromDomainToEntity(customer3);
        List<Customer> customerList = List.of(customer1,customer2,customer3);
        when(customerRepository.saveAndFlush(any())).thenReturn(customerEntity1,customerEntity2,customerEntity3);
        //when
        List<Customer> listToCheck = customerService.createWithList(customerList);
        //then
        assertThat(listToCheck).usingRecursiveComparison().isEqualTo(customerList);
    }

    @Test
    void createWithArray() {
        //given
        Customer customer1 = new Customer (0L,"Julio","1234","julio@mail.com","Felix 7",CustomerRole.USER);
        CustomerEntity customerEntity1 = CustomerDto.fromDomainToEntity(customer1);
        Customer customer2 = new Customer (0L,"Paco","5678","paco@mail.com","Tierno Galvan 4",CustomerRole.ADMIN);
        CustomerEntity customerEntity2 = CustomerDto.fromDomainToEntity(customer2);
        Customer customer3 = new Customer (0L,"Juana","3456","juana@mail.com","Sacrificio 10",CustomerRole.USER);
        CustomerEntity customerEntity3 = CustomerDto.fromDomainToEntity(customer3);
        Customer[] customerArray = new Customer[]{customer1,customer2,customer3};
        when(customerRepository.saveAndFlush(any())).thenReturn(customerEntity1,customerEntity2,customerEntity3);
        //when
        Customer[] arrayToCheck = customerService.createWithArray(customerArray);
        //then
        assertThat(arrayToCheck).usingRecursiveComparison().isEqualTo(customerArray);
    }
}