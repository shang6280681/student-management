package com.example.studentmanagement.entity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import java.util.*;
@Entity @Table(name="students")
public class Student {
 @Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;
 @NotBlank @Column(nullable=false,length=100) private String name;
 @NotBlank @Email @Column(nullable=false,unique=true) private String email;
 @ManyToMany(fetch=FetchType.LAZY)
 @JoinTable(name="student_course",joinColumns=@JoinColumn(name="student_id"),inverseJoinColumns=@JoinColumn(name="course_id"))
 private Set<Course> courses=new HashSet<>();
 public Long getId(){return id;} public void setId(Long id){this.id=id;}
 public String getName(){return name;} public void setName(String name){this.name=name;}
 public String getEmail(){return email;} public void setEmail(String email){this.email=email;}
 public Set<Course> getCourses(){return courses;} public void setCourses(Set<Course> courses){this.courses=courses;}
}
