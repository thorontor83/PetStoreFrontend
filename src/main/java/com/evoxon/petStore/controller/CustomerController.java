package com.evoxon.petStore.controller;

import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.domain.customer.CustomerServiceImpl;
import com.evoxon.petStore.jwt.TokenUtil;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

@RestController
@RequestMapping
public class CustomerController {

    private final CustomerServiceImpl customerServiceImpl;

    public CustomerController(CustomerServiceImpl customerServiceImpl) {
        this.customerServiceImpl = customerServiceImpl;
    }

    @GetMapping(path = "/api/v1/customer/{username}")
    public ResponseEntity<Object> getCustomerByName (@PathVariable String username, @RequestHeader HttpHeaders httpHeaders){
        Customer customer = TokenUtil.getDataFromAccessToken(httpHeaders);

        if (username == null || !username.equals(customer.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Username is not valid");
        }
        Customer customerToGet = customerServiceImpl.getCustomerByName(username);
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
            Customer customerModified = customerServiceImpl.modifyCustomer(customerToModified);
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
        Customer customerCreated = customerServiceImpl.createCustomer(customer);
        if (customerCreated == null){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The customer was not created");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(customerCreated);
        }
    }

    @PostMapping(path = "/api/v1/customer/createWithList")
    public ResponseEntity<Object> createWithList (@RequestBody List<Customer> customerList){
        List<Customer> customerListCreated = customerServiceImpl.createWithList(customerList);
        if (customerListCreated.isEmpty()){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The customers were not created");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(customerListCreated);
        }
    }

    @PostMapping(path = "/api/v1/customer/createWithArray")
    public ResponseEntity<Object> createWithArray (@RequestBody Customer[] customerArray){
        Customer[] customerArrayCreated = customerServiceImpl.createWithArray(customerArray);
        if (customerArrayCreated.length == 0){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("The customers were not created");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(customerArrayCreated);
        }
    }

    @DeleteMapping(path = "/api/v1/customer/{username}")
    public ResponseEntity<Object> deleteCustomer (@PathVariable String username, @RequestHeader HttpHeaders httpHeaders){
        Customer customer = TokenUtil.getDataFromAccessToken(httpHeaders);
        if (username == null || !username.equals(customer.getUsername())){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Invalid username supplied");
        }
        String message = customerServiceImpl.deleteCustomer(customer.getUsername());
        if (message == null){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Customer not found");
        }
        else{
            return ResponseEntity.status(HttpStatus.OK).body(message);

        }
    }

}
