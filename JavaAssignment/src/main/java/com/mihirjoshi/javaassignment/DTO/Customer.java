package com.mihirjoshi.javaassignment.DTO;

import lombok.Data;

import java.util.UUID;

@Data
public class Customer {
    private String customerId;
    private String firstName;
    private String lastName;
    private String street;
    private String address;
    private String city;
    private String state;
    private String email;
    private String phone;
}
