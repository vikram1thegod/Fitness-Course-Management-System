package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Student;
import com.examly.springapp.repository.StudentRepo;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Student saveStudent(Student student) {
        return studentRepo.save(student);
    }

    public List<Student> getAllStudents() {
        return studentRepo.findAll();
    }

    public Optional<Student> getStudentById(Long id) {
        return studentRepo.findById(id);
    }

    public Student updateStudent(Long id, Student student) {
        Student existing = studentRepo.findById(id).orElseThrow();
        existing.setStudentName(student.getStudentName());
        existing.setEmail(student.getEmail());
        existing.setPhoneNumber(student.getPhoneNumber());
        existing.setAddress(student.getAddress());
        return studentRepo.save(existing);
    }

    
    public Optional<Student> getStudentByEmail(String email) {
        return studentRepo.findByEmail(email);
    }
}
