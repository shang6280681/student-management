package com.example.studentmanagement.exception;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.*;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;
@RestControllerAdvice
public class GlobalExceptionHandler {
 @ExceptionHandler(ResourceNotFoundException.class) ResponseEntity<Map<String,String>> notFound(ResourceNotFoundException e){return ResponseEntity.status(404).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(DuplicateResourceException.class) ResponseEntity<Map<String,String>> duplicate(DuplicateResourceException e){return ResponseEntity.status(409).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(IllegalArgumentException.class) ResponseEntity<Map<String,String>> invalid(IllegalArgumentException e){return ResponseEntity.badRequest().body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(MethodArgumentNotValidException.class) ResponseEntity<Map<String,String>> validation(MethodArgumentNotValidException e){String msg=e.getBindingResult().getFieldErrors().stream().map(f->f.getField()+": "+f.getDefaultMessage()).collect(Collectors.joining(", "));return ResponseEntity.badRequest().body(Map.of("error",msg));}
 @ExceptionHandler(DataIntegrityViolationException.class) ResponseEntity<Map<String,String>> conflict(DataIntegrityViolationException e){return ResponseEntity.status(409).body(Map.of("error","Data conflict or constraint violation"));}
 @ExceptionHandler(ExternalServiceException.class) ResponseEntity<Map<String,String>> upstream(ExternalServiceException e){return ResponseEntity.status(502).body(Map.of("error",e.getMessage()));}
 @ExceptionHandler(Exception.class) ResponseEntity<Map<String,String>> general(Exception e){return ResponseEntity.status(500).body(Map.of("error","An unexpected server error occurred"));}
}
