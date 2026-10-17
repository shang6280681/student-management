package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.ExternalUserResponse;
import com.example.studentmanagement.exception.ExternalServiceException;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@Service
public class ExternalUserService {
    private final RestTemplate restTemplate;
    private final String baseUrl;

    public ExternalUserService(RestTemplate restTemplate,
            @Value("${external.users.base-url}") String baseUrl) {
        this.restTemplate = restTemplate;
        this.baseUrl = baseUrl;
    }

    public ExternalUserResponse getDemoUser(long id) {
        if (id < 1) throw new IllegalArgumentException("ID must be positive");
        try {
            ExternalUserResponse result = restTemplate.getForObject(
                    baseUrl + "/users/{id}", ExternalUserResponse.class, id);
            // JSONPlaceholder returns an empty object for nonexistent users.
            if (result == null || result.id() == null) {
                throw new ResourceNotFoundException("External demo user not found: " + id);
            }
            return result;
        } catch (HttpClientErrorException.NotFound ex) {
            throw new ResourceNotFoundException("External demo user not found: " + id);
        } catch (RestClientException ex) {
            throw new ExternalServiceException("External demo API is unavailable", ex);
        }
    }
}
