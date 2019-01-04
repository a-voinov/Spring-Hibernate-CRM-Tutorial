package com.tutorial.crm.service;

import com.tutorial.crm.entity.Customer;

import java.util.List;

public interface CustomerService {

    public List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(int customerId);

    void deleteCustomer(int id);

    List<Customer> getCustomers(String searchName);
}
