package com.example.demo.Courses;

import com.example.demo.student.Student;
import com.example.demo.dto.DepartmentDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courses")
public class CoursesController {

    private final CoursesService coursesService;


    @Autowired
    public CoursesController(CoursesService coursesService)
    {
        this.coursesService = coursesService;
    }

    @GetMapping
    public List<Courses> getCourses() {return coursesService.getCourses();}

    @GetMapping(path = "department/{deparment}")
    public DepartmentDto getDepartment(@PathVariable("deparment")String department){
        return coursesService.getDepartment(department);
    }

    @GetMapping(path = "{coursesId}")
    public Courses getCoursesById(@PathVariable("coursesId")Long coursesId) {
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

    @GetMapping(path = "studentInCourses/{coursesId}")
    public List<Student> getStudentInCourse(
            @PathVariable("coursesId")Long coursesId
    ){
        return coursesService.getStudentByCourse(coursesId);
    }
}
