package com.examly.springapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.examly.springapp.model.Course;

@Repository
public interface CourseRepo extends JpaRepository<Course, Long> {

    List<Course> findByInstructor_InstructorId(Long instructorId);

    List<Course> findByLevel(String level);
}

