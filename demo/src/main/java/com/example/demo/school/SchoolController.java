package com.example.demo.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/school")
public class SchoolController {

    private final SchoolService schoolService;

    @Autowired
    public SchoolController(SchoolService schoolService) {this.schoolService = schoolService;}

    @GetMapping(path = "{schoolId}")
    public School getSchool(@PathVariable("schoolId")Long schoolId){
        return schoolService.getSchool(schoolId);
    }
    @GetMapping
    public List<School> getAllSchool() {
        return schoolService.getAllSchool();
    }
    @PostMapping
    public void addNewSchool(@RequestBody School school) {
        schoolService.addNewSchool(school);
    }

    @DeleteMapping(path = "{schoolId}")
    public void deleteSchool(@PathVariable("schoolId")Long schoolId){
        schoolService.deleteSchool(schoolId);
    }
    @PutMapping(path = "{schoolId}")
    public void updateStudent(
            @PathVariable("schoolId") Long schoolId,
            @RequestParam(required = false) String schoolName){
        schoolService.updateSchool(schoolId, schoolName);
    }
}
