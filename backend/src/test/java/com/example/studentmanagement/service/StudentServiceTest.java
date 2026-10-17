package com.example.studentmanagement.service;
import com.example.studentmanagement.dto.*;
import com.example.studentmanagement.entity.*;
import com.example.studentmanagement.exception.*;
import com.example.studentmanagement.repository.*;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;
import static org.mockito.ArgumentMatchers.*;
@ExtendWith(MockitoExtension.class)
class StudentServiceTest {
 @Mock StudentRepository students;@Mock CourseRepository courses;@InjectMocks StudentService service;
 @Test void createsStudent(){StudentRequest req=new StudentRequest("John","john@test.com",Set.of());when(students.save(any(Student.class))).thenAnswer(i->{Student s=i.getArgument(0);s.setId(1L);return s;});assertEquals("John",service.createStudent(req).name());verify(students).save(any());}
 @Test void rejectsDuplicateEmail(){when(students.existsByEmail("john@test.com")).thenReturn(true);assertThrows(DuplicateResourceException.class,()->service.createStudent(new StudentRequest("John","john@test.com",Set.of())));verify(students,never()).save(any());}
 @Test void missingStudent(){when(students.findById(99L)).thenReturn(Optional.empty());assertThrows(ResourceNotFoundException.class,()->service.getStudentById(99L));}
 @Test void rejectsMissingCourse(){when(courses.findAllById(Set.of(99L))).thenReturn(List.of());assertThrows(ResourceNotFoundException.class,()->service.createStudent(new StudentRequest("John","john@test.com",Set.of(99L))));}
}
