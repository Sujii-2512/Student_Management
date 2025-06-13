package com.studentapp.controller;

import com.studentapp.dto.CourseDTO;
import com.studentapp.dto.EnrollmentDTO;
import com.studentapp.service.CourseService;
import com.studentapp.service.EnrollmentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/students")
public class StudentController {
    
    private final CourseService courseService;
    private final EnrollmentService enrollmentService;
    
    @Autowired
    public StudentController(CourseService courseService, EnrollmentService enrollmentService) {
        this.courseService = courseService;
        this.enrollmentService = enrollmentService;
    }
    
    // View all courses
    @GetMapping("/courses")
    public ResponseEntity<List<CourseDTO>> getAllCourses() {
        return ResponseEntity.ok(courseService.getAllCourses());
    }
    
    // View course by id
    @GetMapping("/courses/{id}")
    public ResponseEntity<CourseDTO> getCourseById(@PathVariable Long id) {
        CourseDTO course = courseService.getCourseById(id);
        if (course != null) {
            return ResponseEntity.ok(course);
        }
        return ResponseEntity.notFound().build();
    }
    
    // Enroll in a course
    @PostMapping("/enroll")
    public ResponseEntity<EnrollmentDTO> enrollInCourse(@Valid @RequestBody EnrollmentDTO enrollmentDTO) {
        return new ResponseEntity<>(enrollmentService.createEnrollment(enrollmentDTO), HttpStatus.CREATED);
    }
    
    // View own enrollments
    @GetMapping("/enrollments")
    public ResponseEntity<List<EnrollmentDTO>> getMyEnrollments(Authentication authentication) {
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        // We need to get the student ID from the email
        // This is a simplified version, in a real app you would use a user service to get the ID
        // Here we're just passing a placeholder ID
        Long studentId = 1L; // This should be replaced with actual student ID lookup
        return ResponseEntity.ok(enrollmentService.getEnrollmentsByStudentId(studentId));
    }
} 