package com.example.studentmanagement.service;
import com.example.studentmanagement.dto.*;
import com.example.studentmanagement.entity.Course;
import com.example.studentmanagement.exception.*;
import com.example.studentmanagement.repository.*;
import java.util.List;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class CourseService {
 private final CourseRepository courses;private final StudentRepository students;
 public CourseService(CourseRepository courses,StudentRepository students){this.courses=courses;this.students=students;}
 @Transactional public CourseResponse createCourse(CourseRequest req){if(courses.existsByName(req.name()))throw new DuplicateResourceException("Course name already exists");Course c=new Course();c.setName(req.name());c.setDescription(req.description());return response(courses.save(c));}
 @Transactional(readOnly=true) public List<CourseResponse> getAllCourses(){return courses.findAll().stream().map(this::response).toList();}
 @Transactional(readOnly=true) public CourseResponse getCourseById(Long id){return response(find(id));}
 @Transactional public CourseResponse updateCourse(Long id,CourseRequest req){Course c=find(id);courses.findByName(req.name()).filter(other->!other.getId().equals(id)).ifPresent(other->{throw new DuplicateResourceException("Course name already exists");});c.setName(req.name());c.setDescription(req.description());return response(courses.save(c));}
 @Transactional public void deleteCourse(Long id){Course c=find(id);students.deleteCourseEnrollments(id);courses.delete(c);}
 private Course find(Long id){return courses.findById(id).orElseThrow(()->new ResourceNotFoundException("Course not found: "+id));}
 private CourseResponse response(Course c){return new CourseResponse(c.getId(),c.getName(),c.getDescription());}
}
