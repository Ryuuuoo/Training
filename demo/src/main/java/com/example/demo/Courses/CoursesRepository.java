package com.example.demo.Courses;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface CoursesRepository
        extends JpaRepository<Courses, Long> {
    List<Courses> findCoursesByDepartment(String department);
}
