package com.evoxon.petStore.controller;

import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.domain.customer.CustomerService;
import io.swagger.oas.inflector.models.ResponseContext;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "/api/v1/customer/{username}")
    public ResponseEntity<Object> getCustomerByName (@PathVariable String username){
        if (username == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is not valid");
        }
        Customer customer = customerService.getCustomerByName(username);
        if (customer != null){
            return ResponseEntity.status(HttpStatus.OK).body(customer);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PostMapping(path = "/api/v1/customer")
    public ResponseEntity<Object> createCustomer (@RequestBody Customer customer){
        Customer customerCreated = customerService.createCustomer(customer);
        if (customerCreated == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The customer was not created");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(customerCreated);
        }
    }

    @DeleteMapping(path = "/api/v1/customer/{username}")
    public ResponseEntity<Object> deleteCustomer (@PathVariable String username){
        if (username == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username supplied");
        }
        String message = customerService.deleteCustomer(username);
        if (message == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(message);

        }
    }

}
