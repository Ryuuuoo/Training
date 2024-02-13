package com.example.demo.school;

import com.example.demo.student.Student;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table
public class School {
    @Id
    @SequenceGenerator(
            name = "school_sequence",
            sequenceName = "school_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = GenerationType.SEQUENCE,
            generator = "school_sequence"
    )
    private Long id;


    private String schoolName;


    public School() {

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }



    @OneToMany(mappedBy = "school", cascade = CascadeType.ALL)
    private List<Student> school = new ArrayList<>();

    public List<Student> getSchool() {
        return school;
    }
}
