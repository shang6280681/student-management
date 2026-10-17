package com.example.studentmanagement.controller;

import com.example.studentmanagement.dto.ExternalUserResponse;
import com.example.studentmanagement.service.ExternalUserService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/external/demo-users")
public class ExternalUserController {
    private final ExternalUserService service;

    public ExternalUserController(ExternalUserService service) {
        this.service = service;
    }

    @GetMapping("/{id}")
    public ExternalUserResponse getDemoUser(@PathVariable long id) {
        return service.getDemoUser(id);
    }
}
