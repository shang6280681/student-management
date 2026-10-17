package com.example.studentmanagement.dto;

// JSONPlaceholder's public demo user, not a Student entity in our database.
public record ExternalUserResponse(Long id, String name, String email) {}
