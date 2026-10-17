package com.example.studentmanagement.repository;
import com.example.studentmanagement.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;
public interface CourseRepository extends JpaRepository<Course,Long> {
 Optional<Course> findByName(String name);
 boolean existsByName(String name);
}
