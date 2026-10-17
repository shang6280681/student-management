package com.example.studentmanagement.dto;
import jakarta.validation.constraints.*;
public record CourseRequest(@NotBlank @Size(max=100) String name,@Size(max=500) String description) {}
