package com.evoxon.petStore.security;

import com.evoxon.petStore.domain.customer.CustomerRole;

public class JWTTokenPayload {

    private String username;
    private CustomerRole customerRole;

    public JWTTokenPayload() {
    }

    public JWTTokenPayload(String username, CustomerRole customerRole) {
        this.username = username;
        this.customerRole = customerRole;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public CustomerRole getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(CustomerRole customerRole) {
        this.customerRole = customerRole;
    }
}
