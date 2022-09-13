package com.evoxon.petStore.persistence;


import com.evoxon.petStore.domain.customer.CustomerRole;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "customers")
public class CustomerEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String username;
    @JsonIgnore
    private String password;
    private String email;
    private String address;
    private CustomerRole customerRole;

    public CustomerEntity() {
    }

    public CustomerEntity(Long id, String username, String password, String email, String address, CustomerRole customerRole) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.customerRole = customerRole;
    }

    public CustomerEntity(String username, String password, String email, String address, CustomerRole customerRole) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.customerRole = customerRole;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public CustomerRole getCustomerRole() {
        return customerRole;
    }

    public void setCustomerRole(CustomerRole customerRole) {
        this.customerRole = customerRole;
    }

    @Override
    public String toString() {
        return "CustomerEntity{" +
                "id=" + id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", address='" + address + '\'' +
                ", customerRole=" + customerRole +
                '}';
    }
}
