package com.mihirjoshi.javaassignment.serivces.authentication;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpEntity;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class AuthenticationService {

    private final RestTemplate restTemplate;

    @Autowired
    public AuthenticationService(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }

    public String authenticateAndGetBearerToken(String loginId, String password) {
        String authenticationUrl = "https://qa2.sunbasedata.com/sunbase/portal/api/assignment_auth.jsp";

        // Create the authentication request object
        AuthRequest authRequest = new AuthRequest(loginId, password);

        // Set up the headers for the response
        HttpHeaders headers = new HttpHeaders();

        // Create the HTTP request entity with the request body and headers
        HttpEntity<AuthRequest> request = new HttpEntity<>(authRequest, headers);

        // Make the authentication request
        ResponseEntity<String> response = restTemplate.postForEntity(authenticationUrl, request, String.class);

        if (response.getStatusCode().is2xxSuccessful()) {
            // Extract the Bearer token from the response headers
            String bearerToken = response.getHeaders().get("Authorization").get(0);
            return bearerToken;
        } else {
            // Handle the authentication failure
            throw new RuntimeException("Authentication failed");
        }
    }
}
