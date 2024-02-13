package com.example.demo.student;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentService studentService;

    @Autowired
    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public List<Student> getStudents() {
        return studentService.getStudents();
    }


    @GetMapping("/{studentId}") // Correcting the mapping
    public Student   getStudentById(@PathVariable("studentId") Long studentId) { // Correcting the annotation placement
        return studentService.findStudentById(studentId); // Correcting the method invocation
    }

    @PostMapping
    public void registerNewStudent(@RequestBody Student student) {
        studentService.addNewStudent(student);
    }

    @DeleteMapping(path = "{studentId}")
    public void deleteStudent(@PathVariable("studentId") Long studentId) {
        studentService.deleteStudent(studentId);
    }

    @PutMapping(path = "{studentId}")
    public void updateStudent(
            @PathVariable("studentId") Long studentId,
            @RequestParam(required = false) String name,
            @RequestParam(required = false) String email) {
        studentService.updateStudent(studentId, name, email);
    }

    @PostMapping(path = "{studentId}/courses/{coursesId}")
    public void enrollStudent(
            @PathVariable Long studentId,
            @PathVariable Long coursesId){
        studentService.enrollStudent(studentId,coursesId);
    }

    @PostMapping(path = "{studentId}/school/{schoolId}")
    public void enrollStudentToSchool(
            @PathVariable Long studentId,
            @PathVariable Long schoolId){
        studentService.enrollStudentToSchool(studentId,schoolId);
    }

}
