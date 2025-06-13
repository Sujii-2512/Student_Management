package com.studentapp.integration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.studentapp.dto.EnrollmentDTO;
import com.studentapp.model.Course;
import com.studentapp.model.Student;
import com.studentapp.repository.CourseRepository;
import com.studentapp.repository.StudentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
public class EnrollmentIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private StudentRepository studentRepository;

    @Autowired
    private CourseRepository courseRepository;

    private Student student;
    private Course course;

    @BeforeEach
    public void setup() {
        // Create a test student
        student = new Student();
        student.setName("Test Student");
        student.setEmail("test@example.com");
        student.setPassword("password");
        student.setRole("STUDENT");
        studentRepository.save(student);

        // Create a test course
        course = new Course();
        course.setTitle("Test Course");
        course.setDescription("Test Description");
        courseRepository.save(course);
    }

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testCreateEnrollment() throws Exception {
        // Create enrollment DTO
        EnrollmentDTO enrollmentDTO = new EnrollmentDTO();
        enrollmentDTO.setStudentId(student.getId());
        enrollmentDTO.setCourseId(course.getId());

        // Perform API call
        mockMvc.perform(post("/api/students/enroll")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(enrollmentDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.studentId").value(student.getId()))
                .andExpect(jsonPath("$.courseId").value(course.getId()));
    }
} 