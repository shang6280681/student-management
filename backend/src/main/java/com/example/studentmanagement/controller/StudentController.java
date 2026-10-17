package com.example.studentmanagement.controller;
import com.example.studentmanagement.dto.*;
import com.example.studentmanagement.service.StudentService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/students") @CrossOrigin(origins="http://localhost:4200")
public class StudentController {
 private final StudentService service;
 public StudentController(StudentService service){this.service=service;}
 @GetMapping public List<StudentResponse> all(){return service.getAllStudents();}
 @GetMapping("/{id}") public StudentResponse get(@PathVariable Long id){return service.getStudentById(id);}
 @PostMapping public ResponseEntity<StudentResponse> create(@Valid @RequestBody StudentRequest req){return ResponseEntity.status(HttpStatus.CREATED).body(service.createStudent(req));}
 @PutMapping("/{id}") public StudentResponse update(@PathVariable Long id,@Valid @RequestBody StudentRequest req){return service.updateStudent(id,req);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.deleteStudent(id);}
}
