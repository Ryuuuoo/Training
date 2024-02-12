package com.example.demo.Courses;

import com.example.demo.student.StudentRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.List;

@Configuration
public class CoursesConfig {
    @Bean
    CommandLineRunner commandLineRunner1(CoursesRepository repository) {
        return args -> {
            Courses HR = new Courses(
                    "HR",
                    "Human Resources"
            );
            Courses Finance = new Courses(
                    "Maths",
                    "Business"
            );

            repository.saveAll(
                    List.of(HR,Finance)
            );
        };
    }
}
