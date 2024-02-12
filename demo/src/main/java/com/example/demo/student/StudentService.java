package com.example.demo.student;

import com.example.demo.Courses.Courses;
import com.example.demo.Courses.CoursesRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.Set;

@Service
public class StudentService {

    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Autowired
    private final StudentRepository studentRepository;

    @Autowired
    private CoursesRepository coursesRepository;

    public List<Student> getStudents() {
        return studentRepository.findAll();
    }
    // this line is to make sure it checks if the email exists in the database or not.
    public void addNewStudent(Student student) {
        Optional<Student> studentOptional = studentRepository.findStudentByEmail(student.getEmail());
        if (studentOptional.isPresent()) {
            throw new IllegalStateException("Email is Taken");
        }
        studentRepository.save(student);
    }

    public void deleteStudent(Long studentId) {
        studentRepository.findById(studentId);
            boolean exists = studentRepository.existsById(studentId);
            if (!exists) {
                throw new IllegalStateException("Student with id " + studentId + " doesnt exist");
            }
            studentRepository.deleteById(studentId);
    }

    @Transactional
    public void updateStudent(Long studentId, String name, String email) {
        Student student = studentRepository.findById(studentId)
                .orElseThrow(() -> new IllegalStateException("Student with id " + studentId + " doesnt not exist"));

        if (name != null && !name.isEmpty() && !Objects.equals(student.getName() , name)){
            student.setName(name);
        }

        if (email != null && !email.isEmpty() && !Objects.equals(student.getEmail(), email)){
            Optional<Student> studentOptional = studentRepository.findStudentByEmail(email);
            if (studentOptional.isPresent()){
                throw new IllegalStateException("email is taken");
            }
            student.setEmail(email);
        }
    }

    public Optional<Student> findStudentById(Long studentId) {
        Optional<Student> studentOptional = studentRepository.findById(studentId);
        if(studentOptional.isEmpty()){
            throw new IllegalStateException("Student Id not found");
        }
        return studentRepository.findById(studentId);
    }

    public void enrollStudent(Long studentId, Long coursesId) {
        Student student = studentRepository.findById(studentId)
                        .orElseThrow(() -> new IllegalStateException("Student id " + studentId + " doesnt not exist"));
        Courses courses = coursesRepository.findById(coursesId)
                        .orElseThrow(() -> new IllegalStateException("doesnt exist"));

        student.getCourses().add(courses);
        courses.getEnrolledStudents().add(student);
        studentRepository.save(student);
    }
}