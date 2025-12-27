package com.examly.springapp.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.examly.springapp.model.Course;
import com.examly.springapp.repository.CourseRepo;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    public Course saveCourse(Course course) {
        return courseRepo.save(course);
    }

    public List<Course> getAllCourses() {
        return courseRepo.findAll();
    }

    public Optional<Course> getCourseById(Long id) {
        return courseRepo.findById(id);
    }

    public Course updateCourse(Long id, Course course) {
        Course existing = courseRepo.findById(id).orElseThrow();
        existing.setCourseName(course.getCourseName());
        existing.setDescription(course.getDescription());
        existing.setDuration(course.getDuration());
        existing.setPrice(course.getPrice());
        existing.setLevel(course.getLevel());
        existing.setInstructor(course.getInstructor());
        return courseRepo.save(existing);
    }


    public List<Course> getCoursesByInstructor(Long instructorId) {
        return courseRepo.findByInstructor_InstructorId(instructorId);
    }

 
    public List<Course> getCoursesByLevel(String level) {
        return courseRepo.findByLevel(level);
    }
}