package com.example.demo.Courses;

import com.example.demo.student.Student;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class CoursesService {
    public CoursesService(CoursesRepository coursesRepository) { this.coursesRepository = coursesRepository; }

    @Autowired
    private final CoursesRepository coursesRepository;

    public List<Courses> getCourses() { return coursesRepository.findAll();}

    public void AddNewCourse(Courses courses) {
            coursesRepository.save(courses);
    }

    public void deleteCourse(Long coursesId) {
        coursesRepository.findById(coursesId);
        boolean exists = coursesRepository.existsById(coursesId);
        if (!exists) {
            throw new IllegalStateException("Student with id" + coursesId + " exists");
        }
        coursesRepository.deleteById(coursesId);
    }

    @Transactional
    public void updateCourses(Long courseId, String courseName, String department) {
        Courses courses = coursesRepository.findById(courseId)
                .orElseThrow(() -> new IllegalStateException("Course with id " + courseId + " does not exist"));

        if (courseName != null && !courseName.isEmpty() && !Objects.equals(courses.getCourseName(), courseName)) {
            courses.setCourseName(courseName);
        }

        if (department != null && !department.isEmpty() && !Objects.equals(courses.getDepartment(), department)) {
            courses.setDepartment(department);
        }

        coursesRepository.save(courses);
    }

    public Optional<Courses> findCourseById(Long coursesId) {
        Optional<Courses> coursesOptional = coursesRepository.findById(coursesId);
        if (coursesOptional.isEmpty()){
            throw new IllegalStateException("Id does not exist");
        }
        return coursesRepository.findById(coursesId);
    }
}
