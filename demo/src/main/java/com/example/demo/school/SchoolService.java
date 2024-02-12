package com.example.demo.school;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SchoolService {

    @Autowired
    private final SchoolRepository schoolRepository;


    public void addNewSchool(School school) {
        schoolRepository.save(school);
    }
    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public School getSchool(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public List<School> getAllSchool() {
        return schoolRepository.findAll();
    }
}
