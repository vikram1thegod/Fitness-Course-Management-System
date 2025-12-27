package com.examly.springapp.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.examly.springapp.model.Instructor;
import com.examly.springapp.service.InstructorService;

@RestController
@RequestMapping("/api/instructors")
public class InstructorController {

    private final InstructorService instructorService;

    @Autowired
    public InstructorController(InstructorService instructorService) {
        this.instructorService = instructorService;
    }


    @PostMapping
    public ResponseEntity<Instructor> createInstructor(@RequestBody Instructor instructor) {
        if (instructor == null) {
            return ResponseEntity.badRequest().build();
        }
        Instructor saved = instructorService.createInstructor(instructor);
        return new ResponseEntity<>(saved, HttpStatus.CREATED);
    }

    
    @GetMapping
    public ResponseEntity<List<Instructor>> getAllInstructors() {
        List<Instructor> instructors = instructorService.getAllInstructors();
        if (instructors.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(instructors);
    }

    
    @GetMapping("/{id}")
    public ResponseEntity<Instructor> getInstructorById(@PathVariable Long id) {
        Optional<Instructor> instructor = instructorService.getInstructorById(id);
        return instructor.map(ResponseEntity::ok)
                         .orElseGet(() -> ResponseEntity.notFound().build());
    }

    
    @PutMapping("/{id}")
    public ResponseEntity<Instructor> updateInstructor(@PathVariable Long id,
                                                       @RequestBody Instructor instructor) {
        Instructor updated = instructorService.updateInstructor(id, instructor);
        if (updated == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(updated);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInstructor(@PathVariable Long id) {
        instructorService.deleteInstructor(id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/page/{page}/{size}")
    public ResponseEntity<Page<Instructor>> getInstructorsWithPagination(@PathVariable int page,
                                                                         @PathVariable int size) {
        Page<Instructor> result = instructorService.getInstructorsWithPagination(page, size);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/specialization/{specialization}")
public ResponseEntity<?> getInstructorsBySpecialization(@PathVariable String specialization) {
    List<Instructor> instructors = instructorService.getInstructorsBySpecialization(specialization);

    if (instructors.isEmpty()) {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body("No instructors found with specialization: " + specialization);
    }

    return ResponseEntity.ok(instructors);
}

}
