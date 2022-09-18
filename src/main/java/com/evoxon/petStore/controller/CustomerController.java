package com.evoxon.petStore.controller;

import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.domain.customer.CustomerService;
import com.evoxon.petStore.jwt.TokenUtil;
import io.swagger.oas.inflector.models.ResponseContext;
import org.springframework.http.HttpHeaders;
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
    public ResponseEntity<Object> getCustomerByName (@PathVariable String username, @RequestHeader HttpHeaders httpHeaders){
        Customer customer = TokenUtil.getDataFromAccessToken(httpHeaders);

        if (username == null || !username.equals(customer.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is not valid");
        }
        Customer customerToGet = customerService.getCustomerByName(username);
        if (customerToGet != null){
            return ResponseEntity.status(HttpStatus.OK).body(customerToGet);
        }
        else{
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
        }
    }

    @PutMapping(path = "/api/v1/customer/{username}")
    public ResponseEntity<Object> modifyCustomer (@RequestBody Customer customerToModified, @RequestHeader HttpHeaders httpHeaders){
        Customer customer = TokenUtil.getDataFromAccessToken(httpHeaders);
        if (customerToModified.getUsername() == null || !customerToModified.getUsername().equals(customer.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is not valid");
        }
        else{
            Customer customerModified = customerService.modifyCustomer(customerToModified);
            if (customerModified == null){
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("User not found");
            }
            else{
                return ResponseEntity.status(HttpStatus.OK).body(customerModified);
            }
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
    public ResponseEntity<Object> deleteCustomer (@PathVariable String username, @RequestHeader HttpHeaders httpHeaders){
        Customer customer = TokenUtil.getDataFromAccessToken(httpHeaders);
        if (username == null || !username.equals(customer.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username supplied");
        }
        String message = customerService.deleteCustomer(customer.getUsername());
        if (message == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(message);

        }
    }

}
