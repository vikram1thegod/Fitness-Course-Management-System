package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.examly.springapp.model.Instructor;

@Repository
public interface InstructorRepo extends JpaRepository<Instructor, Long> {
}
