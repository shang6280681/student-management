package com.example.studentmanagement.service;
import com.example.studentmanagement.dto.*;
import com.example.studentmanagement.entity.*;
import com.example.studentmanagement.exception.*;
import com.example.studentmanagement.repository.*;
import java.util.*;
import java.util.stream.Collectors;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class StudentService {
 private final StudentRepository students; private final CourseRepository courses;
 public StudentService(StudentRepository students,CourseRepository courses){this.students=students;this.courses=courses;}
 @Transactional public StudentResponse createStudent(StudentRequest req){
  if(students.existsByEmail(req.email())) throw new DuplicateResourceException("Email already exists");
  Student s=new Student();s.setName(req.name());s.setEmail(req.email());s.setCourses(resolveCourses(req.courseIds()));return toResponse(students.save(s));
 }
 @Transactional(readOnly=true) public List<StudentResponse> getAllStudents(){return students.findAll().stream().map(this::toResponse).toList();}
 @Transactional(readOnly=true) public StudentResponse getStudentById(Long id){return toResponse(find(id));}
 @Transactional public StudentResponse updateStudent(Long id,StudentRequest req){
  Student s=find(id);
  students.findByEmail(req.email()).filter(other->!other.getId().equals(id)).ifPresent(other->{throw new DuplicateResourceException("Email already exists");});
  s.setName(req.name());s.setEmail(req.email());s.setCourses(resolveCourses(req.courseIds()));return toResponse(students.save(s));
 }
 @Transactional public void deleteStudent(Long id){students.delete(find(id));}
 private Student find(Long id){return students.findById(id).orElseThrow(()->new ResourceNotFoundException("Student not found: "+id));}
 private Set<Course> resolveCourses(Set<Long> ids){
  List<Course> found=courses.findAllById(ids);
  Set<Long> foundIds=found.stream().map(Course::getId).collect(Collectors.toSet());
  if(!foundIds.containsAll(ids)) throw new ResourceNotFoundException("One or more courses not found");
  return new HashSet<>(found);
 }
 private StudentResponse toResponse(Student s){return new StudentResponse(s.getId(),s.getName(),s.getEmail(),s.getCourses().stream().map(Course::getId).collect(Collectors.toSet()));}
}
