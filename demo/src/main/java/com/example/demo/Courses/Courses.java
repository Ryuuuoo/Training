package com.example.demo.Courses;

import com.example.demo.student.Student;
import jakarta.persistence.*;

import java.util.HashSet;
import java.util.Set;


@Entity
@Table
public class Courses {
    @Id
    @SequenceGenerator(
            name = "courses_sequence",
            sequenceName = "courses_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "courses_sequence"
    )
    private Long Id;

    private String courseName;

    private String Department;
    public Courses() {
    }

    public Courses(String courseName, String department) {
        this.courseName = courseName;
        Department = department;
    }
    public Courses(Long id, String courseName, String department) {
        Id = id;
        this.courseName = courseName;
        Department = department;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public String getDepartment() {
        return Department;
    }

    public void setDepartment(String department) {
        Department = department;
    }

    @ManyToMany
    @JoinTable(
            name = "student_enrolled",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "student_id")
    )
    private Set<Student> enrolledStudents = new HashSet<>();

    public Set<Student> getEnrolledStudents() {
        return enrolledStudents;
    }

}
