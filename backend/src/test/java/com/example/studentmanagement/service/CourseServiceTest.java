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
class CourseServiceTest {
 @Mock CourseRepository courses;@Mock StudentRepository students;@InjectMocks CourseService service;
 @Test void createsCourse(){when(courses.save(any(Course.class))).thenAnswer(i->{Course c=i.getArgument(0);c.setId(1L);return c;});assertEquals("Java",service.createCourse(new CourseRequest("Java","Basics")).name());}
 @Test void rejectsDuplicate(){when(courses.existsByName("Java")).thenReturn(true);assertThrows(DuplicateResourceException.class,()->service.createCourse(new CourseRequest("Java","Basics")));}
 @Test void missingCourse(){when(courses.findById(99L)).thenReturn(Optional.empty());assertThrows(ResourceNotFoundException.class,()->service.getCourseById(99L));}
}
