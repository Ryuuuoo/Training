package com.example.demo.dto;

import com.example.demo.Courses.Courses;

import java.util.List;

public class DepartmentDto {

    public DepartmentDto(String departmentName, List<Courses> courses) {
        this.departmentName = departmentName;
        this.courses = courses;
    }

    private String departmentName;

    private List<Courses> courses;

    public String getDepartmentName() {
        return departmentName;
    }

    public void setDepartmentName(String departmentName) {
        this.departmentName = departmentName;
    }

    public List<Courses> getCourses() {
        return courses;
    }

    public void setCourses(List<Courses> courses) {
        this.courses = courses;
    }
}
