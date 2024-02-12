package com.example.demo.Courses;

import com.example.demo.student.Student;
import com.example.demo.student.StudentController;
import com.example.demo.student.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CoursesService coursesService;
    private final StudentService studentService;


    @Autowired
    public CoursesController(CoursesService coursesService , StudentService studentService)
    {
        this.coursesService = coursesService;
        this.studentService = studentService;
    }

    @GetMapping
    public List<Courses> getCourses() {return coursesService.getCourses();}

    @GetMapping(path = "{coursesId}")
    public Optional<Courses> getCoursesById(@PathVariable("coursesId")Long coursesId) {
        return coursesService.findCourseById(coursesId);
    }


    @PostMapping
    public void AddNewCourse(@RequestBody Courses courses) {coursesService.AddNewCourse(courses);}

    @DeleteMapping(path = "{coursesId}")
    public void deleteCourse(@PathVariable("coursesId")Long coursesId) {coursesService.deleteCourse(coursesId);}

    @PutMapping(path = "{coursesId}")
    public void updateCourses(
            @PathVariable("coursesId")Long coursesId,
            @RequestParam(required = false) String courseName,
            @RequestParam(required = false) String Department){
        coursesService.updateCourses(coursesId,courseName,Department);
    }

}
