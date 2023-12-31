package com.mihirjoshi.javaassignment.controller;

import com.mihirjoshi.javaassignment.DTO.Customer;
import com.mihirjoshi.javaassignment.DTO.User;
import com.mihirjoshi.javaassignment.serivces.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/customers-backend")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    private final User adminUser = new User("admin", "admin");
    @PostMapping("/login")
    public ResponseEntity<String> login(@RequestBody User user) {
        if (adminUser.getUsername().equals(user.getUsername()) && adminUser.getPassword().equals(user.getPassword())) {
            // Authentication successful
            return new ResponseEntity<>("Login successful", HttpStatus.OK);
        } else {
            // Authentication failed
            return new ResponseEntity<>("Login failed", HttpStatus.UNAUTHORIZED);
        }
    }
    @PostMapping("/create")
    public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
        try {
            String customerId = UUID.randomUUID().toString();
            customer.setCustomerId(customerId);
            Customer createdCustomer = customerService.createCustomer(customer);
            return new ResponseEntity<>(createdCustomer, HttpStatus.CREATED);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/get-customer-list")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        try {
            List<Customer> customerList = customerService.getAllCustomers();
            return new ResponseEntity<>(customerList, HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/delete")
    public ResponseEntity<String> deleteCustomer(@RequestBody Customer customer) {
        try {
            customerService.deleteCustomer(customer);
            return new ResponseEntity<>("Customer deleted successfully.", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateCustomer(@RequestBody Customer customer) {
        try {
            customerService.updateCustomerDetails(customer);
            return new ResponseEntity<>("Customer updated successfully.", HttpStatus.OK);
        } catch (IllegalStateException e) {
            return new ResponseEntity<>("Unauthorized", HttpStatus.UNAUTHORIZED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>("Bad Request", HttpStatus.BAD_REQUEST);
        }
    }
}
