package com.example.demo.Courses;

import com.example.demo.student.Student;
import com.example.demo.dto.DepartmentDto;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

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

    public Courses findCourseById(Long coursesId) {
        Optional<Courses> coursesOptional = coursesRepository.findById(coursesId);
        if (coursesOptional.isEmpty()){
            throw new IllegalStateException("Id does not exist");
        }
        return coursesOptional.orElse(null);
    }
    public void saveEnrolledStudents(Courses courses){
        coursesRepository.save(courses);
    }

    public DepartmentDto getDepartment(String department) {
        List<Courses> coursesByDepartment = coursesRepository.findCoursesByDepartment(department);

        return new DepartmentDto(department,coursesByDepartment);

    }

    public List<Student> getStudentByCourse(Long coursesId){
        Courses courses = findCourseById(coursesId);
        return courses.getEnrolledStudents();
    }

}
