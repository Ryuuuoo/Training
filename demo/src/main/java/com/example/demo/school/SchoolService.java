package com.example.demo.school;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class SchoolService {

    @Autowired
    private final SchoolRepository schoolRepository;


    public SchoolService(SchoolRepository schoolRepository) {
        this.schoolRepository = schoolRepository;
    }

    public void addNewSchool(School school) {
        schoolRepository.save(school);
    }

    public School getSchool(Long id) {
        return schoolRepository.findById(id).orElse(null);
    }

    public List<School> getAllSchool() {
        return schoolRepository.findAll();
    }

    public void deleteSchool(Long schoolId){
        School school = getSchool(schoolId);
        if (!schoolRepository.existsById(schoolId)){
            throw new IllegalStateException("School id: " + schoolId + " doesnt exist");
        }
        schoolRepository.deleteById(schoolId);
    }

    public void updateSchool(Long schoolId, String schoolName) {
        School school = getSchool(schoolId);

        if (schoolName != null && !schoolName.isEmpty() && !Objects.equals(school.getSchool() , schoolName)){
            school.setSchoolName(schoolName);
        }
        schoolRepository.save(school);
    }
}
