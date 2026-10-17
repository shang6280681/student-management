package com.example.studentmanagement.repository;
import com.example.studentmanagement.entity.Student;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;
import java.util.Optional;
public interface StudentRepository extends JpaRepository<Student,Long> {
 Optional<Student> findByEmail(String email);
 boolean existsByEmail(String email);
 @Modifying @Query(value="DELETE FROM student_course WHERE course_id = :courseId",nativeQuery=true)
 void deleteCourseEnrollments(@Param("courseId") Long courseId);
}
