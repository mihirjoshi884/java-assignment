package com.mihirjoshi.javaassignment.serivces.serviceImpl;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.mihirjoshi.javaassignment.DTO.Customer;
import com.mihirjoshi.javaassignment.serivces.CustomerService;
import com.mihirjoshi.javaassignment.serivces.authentication.AuthenticationService;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.util.Collections;
import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {


    private final AuthenticationService authService;
    private RestTemplate restTemplate = new RestTemplate();
    private static UriComponentsBuilder builder;
    private static String token;
    private static HttpHeaders headers = new HttpHeaders();
    private static HttpMethod method;
    private static ResponseEntity<String> response;

    public CustomerServiceImpl(AuthenticationService authService, RestTemplate restTemplate) {
        this.authService = authService;
        this.restTemplate = restTemplate;
    }

    @Override
    public void getAuthenticationToken() {
        token = authService.authenticateAndGetBearerToken("test@sunbasedata.com", "Test@123");

    }

    @Override
    public Customer createCustomer(Customer customer) {
        getAuthenticationToken();
        if (token == null || token.isEmpty()) {

            throw new IllegalStateException("Bearer token is missing or not obtained.");
        }
        headers.set("Authorization",token);
        HttpEntity<Customer> request = new HttpEntity<>(customer,headers);

        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=create";
        response = restTemplate.postForEntity(url,request,String.class);
        ResponseEntity<String> response = restTemplate.postForEntity(url, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            String responseBody = response.getBody();
            return customer;
        } else if (response.getStatusCodeValue() == 400) {
            throw new RuntimeException("Failed to create customer. Server responded with a 400 status code.");
        } else {

            throw new RuntimeException("Failed to create customer. Server responded with status code: " + response.getStatusCodeValue());
        }

    }

    @Override
    public List<Customer> getAllCustomers() {
        getAuthenticationToken();
        if (token == null || token.isEmpty()) {
            // Handle the case where the token is missing or not obtained.
            throw new IllegalStateException("Bearer token is missing or not obtained.");
        }
        headers.set("Authorization","Bearer"+token);
        HttpEntity<?> request = new HttpEntity<>(headers);
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp?cmd=get_customer_list";
        method = HttpMethod.GET;
        response = restTemplate.exchange(url, method,request,String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            // Successfully retrieved customer list
            String responseBody = response.getBody();

            // Parse the response JSON and convert it into a list of Customer objects
            List<Customer> customerList = parseCustomerList(responseBody);
            return customerList;
        } else {
            // Handle other possible errors
            throw new RuntimeException("Failed to retrieve customer list. Server responded with status code: " + response.getStatusCodeValue());
        }
    }
    private List<Customer> parseCustomerList(String responseBody) {
        try {
            // Create an instance of the Jackson ObjectMapper
            ObjectMapper objectMapper = new ObjectMapper();

            // Use the TypeReference to specify the target list type
            TypeReference<List<Customer>> typeReference = new TypeReference<List<Customer>>() {};

            // Deserialize the JSON response into a List of Customer objects
            List<Customer> customerList = objectMapper.readValue(responseBody, typeReference);

            return customerList;
        } catch (Exception e) {
            // Handle any exceptions that might occur during parsing
            e.printStackTrace();
            return Collections.emptyList(); // Return an empty list as a fallback
        }
    }
    @Override
    public void deleteCustomer(Customer customer) {
        getAuthenticationToken();
        if (token == null || token.isEmpty()) {

            throw new IllegalStateException("Bearer token is missing or not obtained.");
        }
        headers.set("Authorization","Bearer"+token);

        HttpEntity<Customer> request = new HttpEntity<>(customer,headers);
        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";
        builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("cmd", "delete")
                .queryParam("uuid", customer.getCustomerId());

        String updatedUrl = builder.build().toUriString();
        ResponseEntity<String> response = restTemplate.postForEntity(updatedUrl, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {

            System.out.println("Customer with UUID " + customer.getCustomerId() + " was successfully deleted.");

        } else if (response.getStatusCodeValue() == 400) {

            System.out.println("Failed to delete customer. UUID not found.");
        } else if (response.getStatusCodeValue() == 500) {

            System.out.println("Failed to delete customer. Error: Not deleted.");
        } else {

            System.out.println("Failed to delete customer. Server responded with status code: " + response.getStatusCodeValue());
        }

    }

    @Override
    public void updateCustomerDetails(Customer customer) {
        getAuthenticationToken();
        if (token == null || token.isEmpty()) {

            throw new IllegalStateException("Bearer token is missing or not obtained.");
        }
        headers.set("Authorization","Bearer "+token);
        HttpEntity<Customer> request = new HttpEntity<>(customer,headers);

        String url = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment.jsp";

        builder = UriComponentsBuilder.fromHttpUrl(url)
                .queryParam("cmd", "update")
                .queryParam("uuid", customer.getCustomerId());

        String updatedUrl = builder.build().toUriString();

        ResponseEntity<String> response = restTemplate.postForEntity(updatedUrl, request, String.class);
        if (response.getStatusCode().is2xxSuccessful()) {
            // Successfully updated
            System.out.println("Customer with UUID " + customer.getCustomerId() + " was successfully updated.");
        } else if (response.getStatusCodeValue() == 400) {
            // Handle the case where the server responds with a 400 status code (e.g., body is empty)
            System.out.println("Failed to update customer. Body is empty.");
        } else if (response.getStatusCodeValue() == 500) {
            // Handle the case where the server responds with a 500 status code (e.g., UUID not found)
            System.out.println("Failed to update customer. UUID not found.");
        } else {
            // Handle other possible errors
            System.out.println("Failed to update customer. Server responded with status code: " + response.getStatusCodeValue());
        }


    }
}
