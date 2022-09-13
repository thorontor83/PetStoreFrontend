package com.evoxon.petStore.controller;

import com.evoxon.petStore.domain.customer.Customer;
import com.evoxon.petStore.domain.customer.CustomerService;
import io.swagger.oas.inflector.models.ResponseContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.core.Response;

@RestController
@RequestMapping
public class CustomerController {

    private final CustomerService customerService;

    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @GetMapping(path = "api/v1/customer/{username}")
    public ResponseContext getCustomerByName (@PathVariable String username){
        if (username == null){
            return new ResponseContext().status(Response.Status.BAD_REQUEST).entity("Invalid username supplied");
        }
        Customer customer = customerService.getCustomerByName(username);
        if (customer != null){
            return new ResponseContext().status(Response.Status.OK).entity(customer);
        }
        else{
            return new ResponseContext().status(Response.Status.NOT_FOUND).entity("User not found");
        }
    }

}
