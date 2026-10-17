package com.example.studentmanagement.service;
import com.example.studentmanagement.entity.*;
import com.example.studentmanagement.repository.*;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;
import java.util.HashSet;
import java.util.Set;
import static org.junit.jupiter.api.Assertions.*;
@SpringBootTest @Transactional
class CourseServiceIntegrationTest {
 @Autowired CourseService service;@Autowired CourseRepository courses;@Autowired StudentRepository students;@Autowired EntityManager em;
 @Test void deleteCoursePreservesStudent(){Course c=new Course();c.setName("Java");c=courses.save(c);Long cid=c.getId();Student s=new Student();s.setName("John");s.setEmail("john@test.com");s.setCourses(new HashSet<>(Set.of(c)));s=students.save(s);Long sid=s.getId();em.flush();em.clear();service.deleteCourse(cid);em.flush();em.clear();assertFalse(courses.existsById(cid));assertTrue(students.findById(sid).orElseThrow().getCourses().isEmpty());}
}
