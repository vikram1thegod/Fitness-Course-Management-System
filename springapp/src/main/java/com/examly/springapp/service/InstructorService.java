package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Instructor;
import com.examly.springapp.repository.InstructorRepo;

@Service
public class InstructorService {

    private final InstructorRepo instructorRepo;

    @Autowired
    public InstructorService(InstructorRepo instructorRepo) {
        this.instructorRepo = instructorRepo;
    }


    public Instructor createInstructor(Instructor instructor) {
        return instructorRepo.save(instructor);
    }

    public List<Instructor> getAllInstructors() {
        return instructorRepo.findAll();
    }

    public Optional<Instructor> getInstructorById(Long id) {
        return instructorRepo.findById(id);
    }

    public Instructor updateInstructor(Long id, Instructor instructor) {
        Optional<Instructor> existing = instructorRepo.findById(id);
        if (existing.isPresent()) {
            Instructor updated = existing.get();
            updated.setInstructorName(instructor.getInstructorName());
            updated.setEmail(instructor.getEmail());
            updated.setSpecialization(instructor.getSpecialization());
            updated.setPhoneNumber(instructor.getPhoneNumber());
            return instructorRepo.save(updated);
        }
        return null;
    }

    public void deleteInstructor(Long id) {
        instructorRepo.deleteById(id);
    }

    public Page<Instructor> getInstructorsWithPagination(int page, int size) {
        Pageable pageable = PageRequest.of(page, size);
        return instructorRepo.findAll(pageable);
    }
 
    public List<Instructor> getInstructorsBySpecialization(String specialization) {
        return instructorRepo.findAll().stream()
                             .filter(i -> i.getSpecialization() != null && i.getSpecialization().equalsIgnoreCase(specialization))
                             .toList();
    }
}