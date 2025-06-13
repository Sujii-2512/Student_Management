package com.studentapp.controller;

import com.studentapp.dto.CourseDTO;
import com.studentapp.service.CourseService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
public class CourseControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseService courseService;

    @Test
    @WithMockUser(roles = "STUDENT")
    public void testGetAllCourses() throws Exception {
        // Prepare test data
        CourseDTO course1 = new CourseDTO(1L, "Java Programming", "Learn Java");
        CourseDTO course2 = new CourseDTO(2L, "Spring Boot", "Learn Spring Boot");
        List<CourseDTO> courses = Arrays.asList(course1, course2);

        // Mock service method
        when(courseService.getAllCourses()).thenReturn(courses);

        // Perform request and validate response
        mockMvc.perform(get("/api/students/courses"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].title").value("Java Programming"))
                .andExpect(jsonPath("$[1].title").value("Spring Boot"));
    }
} 