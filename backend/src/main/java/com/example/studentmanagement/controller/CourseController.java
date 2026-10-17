package com.example.studentmanagement.controller;
import com.example.studentmanagement.dto.*;
import com.example.studentmanagement.service.CourseService;
import jakarta.validation.Valid;
import java.util.List;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/courses") @CrossOrigin(origins="http://localhost:4200")
public class CourseController {
 private final CourseService service;
 public CourseController(CourseService service){this.service=service;}
 @GetMapping public List<CourseResponse> all(){return service.getAllCourses();}
 @GetMapping("/{id}") public CourseResponse get(@PathVariable Long id){return service.getCourseById(id);}
 @PostMapping public ResponseEntity<CourseResponse> create(@Valid @RequestBody CourseRequest req){return ResponseEntity.status(HttpStatus.CREATED).body(service.createCourse(req));}
 @PutMapping("/{id}") public CourseResponse update(@PathVariable Long id,@Valid @RequestBody CourseRequest req){return service.updateCourse(id,req);}
 @DeleteMapping("/{id}") @ResponseStatus(HttpStatus.NO_CONTENT) public void delete(@PathVariable Long id){service.deleteCourse(id);}
}
