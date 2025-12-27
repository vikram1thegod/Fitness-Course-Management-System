package com.examly.springapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Enrollment;

@Repository
public interface EnrollmentRepo extends JpaRepository<Enrollment, Long> {
}
