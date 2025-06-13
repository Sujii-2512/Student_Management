package com.studentapp.service;

import com.studentapp.dto.StudentDTO;
import com.studentapp.model.Student;
import com.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class StudentService {
    
    private final StudentRepository studentRepository;
    private final PasswordEncoder passwordEncoder;
    
    @Autowired
    public StudentService(StudentRepository studentRepository, PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.passwordEncoder = passwordEncoder;
    }
    
    public List<StudentDTO> getAllStudents() {
        return studentRepository.findAll().stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }
    
    public StudentDTO getStudentById(Long id) {
        Optional<Student> student = studentRepository.findById(id);
        return student.map(this::convertToDTO).orElse(null);
    }
    
    public StudentDTO createStudent(StudentDTO studentDTO) {
        if (studentRepository.existsByEmail(studentDTO.getEmail())) {
            throw new RuntimeException("Email already exists");
        }
        
        Student student = convertToEntity(studentDTO);
        student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        if (student.getRole() == null || student.getRole().isEmpty()) {
            student.setRole("STUDENT");
        }
        
        Student savedStudent = studentRepository.save(student);
        return convertToDTO(savedStudent);
    }
    
    public StudentDTO updateStudent(Long id, StudentDTO studentDTO) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        
        Student student = convertToEntity(studentDTO);
        student.setId(id);
        
        // Check if password is being updated
        if (studentDTO.getPassword() != null && !studentDTO.getPassword().isEmpty()) {
            student.setPassword(passwordEncoder.encode(studentDTO.getPassword()));
        } else {
            // Keep the existing password
            studentRepository.findById(id).ifPresent(existingStudent -> 
                student.setPassword(existingStudent.getPassword())
            );
        }
        
        Student updatedStudent = studentRepository.save(student);
        return convertToDTO(updatedStudent);
    }
    
    public void deleteStudent(Long id) {
        if (!studentRepository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        studentRepository.deleteById(id);
    }
    
    // Helper methods to convert between Entity and DTO
    private StudentDTO convertToDTO(Student student) {
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setName(student.getName());
        dto.setEmail(student.getEmail());
        // this doesn't include password in DTO for security
        dto.setPassword(null);
        dto.setRole(student.getRole());
        return dto;
    }
    
    private Student convertToEntity(StudentDTO dto) {
        Student student = new Student();
        student.setId(dto.getId());
        student.setName(dto.getName());
        student.setEmail(dto.getEmail());
        student.setPassword(dto.getPassword());
        student.setRole(dto.getRole());
        return student;
    }
} 