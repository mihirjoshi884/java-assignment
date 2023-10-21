package com.mihirjoshi.javaassignment.serivces;

import com.mihirjoshi.javaassignment.DTO.Customer;

import java.util.List;

public interface CustomerService {


    public void getAuthenticationToken();

    public Customer createCustomer(Customer customer);
    public List<Customer> getAllCustomers();
    public void deleteCustomer(Customer customer);
    public void updateCustomerDetails(Customer customer);
}
