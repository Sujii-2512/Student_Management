package com.studentapp.controller;

import com.studentapp.dto.StudentDTO;
import com.studentapp.service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    
    private final StudentService studentService;
    
    @Autowired
    public AuthController(StudentService studentService) {
        this.studentService = studentService;
    }
    
    @PostMapping("/register")
    public ResponseEntity<StudentDTO> registerUser(@Valid @RequestBody StudentDTO studentDTO) {
        // Set role as STUDENT for registration
        studentDTO.setRole("STUDENT");
        return new ResponseEntity<>(studentService.createStudent(studentDTO), HttpStatus.CREATED);
    }
} 