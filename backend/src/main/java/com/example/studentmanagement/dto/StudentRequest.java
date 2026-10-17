package com.example.studentmanagement.dto;
import jakarta.validation.constraints.*;
import java.util.Set;
public record StudentRequest(@NotBlank @Size(max=100) String name,@NotBlank @Email String email,@NotNull Set<@NotNull @Positive Long> courseIds) {}
