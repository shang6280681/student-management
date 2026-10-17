package com.example.studentmanagement.service;

import com.example.studentmanagement.dto.ExternalUserResponse;
import com.example.studentmanagement.exception.ExternalServiceException;
import com.example.studentmanagement.exception.ResourceNotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.web.client.RestTemplate;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.*;
import static org.springframework.test.web.client.response.MockRestResponseCreators.*;

class ExternalUserServiceTest {
    private MockRestServiceServer server;
    private ExternalUserService service;

    @BeforeEach
    void setUp() {
        RestTemplate restTemplate = new RestTemplate();
        server = MockRestServiceServer.createServer(restTemplate);
        service = new ExternalUserService(restTemplate, "https://example.test");
    }

    @Test
    void returnsExternalUser() {
    	server.expect(requestTo("https://example.test/users/1"))
        .andRespond(withSuccess(
            "{\"id\":1,\"name\":\"Leanne Graham\",\"email\":\"leanne@example.test\"}",
            org.springframework.http.MediaType.APPLICATION_JSON
        ));
        ExternalUserResponse response = service.getDemoUser(1);
        assertEquals(1L, response.id());
        assertEquals("Leanne Graham", response.name());
        server.verify();
    }

    @Test
    void returnsNotFoundForMissingUser() {
        server.expect(requestTo("https://example.test/users/999"))
                .andRespond(withStatus(HttpStatus.NOT_FOUND));
        assertThrows(ResourceNotFoundException.class, () -> service.getDemoUser(999));
        server.verify();
    }

    @Test
    void returnsBadGatewayExceptionForUpstreamFailure() {
        server.expect(requestTo("https://example.test/users/1"))
                .andRespond(withStatus(HttpStatus.SERVICE_UNAVAILABLE));
        assertThrows(ExternalServiceException.class, () -> service.getDemoUser(1));
        server.verify();
    }

    @Test
    void rejectsInvalidIdWithoutHttpCall() {
        assertThrows(IllegalArgumentException.class, () -> service.getDemoUser(0));
        server.verify();
    }
}
