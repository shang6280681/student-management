package com.example.studentmanagement.dto;
import java.util.Set;
public record StudentResponse(Long id,String name,String email,Set<Long> courseIds) {}
