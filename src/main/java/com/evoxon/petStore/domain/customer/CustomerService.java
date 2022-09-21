package com.evoxon.petStore.domain.customer;

import java.util.List;

public interface CustomerService {

    public Customer getCustomerByName(String username);

    public Customer createCustomer(Customer customer);

    public String deleteCustomer(String username);

    public Customer modifyCustomer(Customer customer);

    public List<Customer> createWithList(List<Customer> customerList);

    public Customer[] createWithArray(Customer[] customerArray);
}
