package com.mihirjoshi.javaassignment.DTO;

import lombok.Data;

@Data
public class Customer {
    private String customerId;
    private String username;
    private String password;
    private String firstName;
    private String lastName;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
}
