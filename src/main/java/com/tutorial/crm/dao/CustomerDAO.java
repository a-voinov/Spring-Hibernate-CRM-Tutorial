package com.tutorial.crm.dao;

import com.tutorial.crm.entity.Customer;

import java.util.List;

public interface CustomerDAO {
    public List<Customer> getCustomers();

    void saveCustomer(Customer customer);

    Customer getCustomer(int customerId);

    void deleteCustomer(int id);

    List<Customer> getCustomers(String searchName);
}
