package com.studentapp.service;

import com.studentapp.dto.CourseDTO;
import com.studentapp.model.Course;
import com.studentapp.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class CourseServiceTest {

    @Mock
    private CourseRepository courseRepository;

    @InjectMocks
    private CourseService courseService;

    private Course course;
    private CourseDTO courseDTO;

    @BeforeEach
    public void setup() {
        course = new Course();
        course.setId(1L);
        course.setTitle("Java Programming");
        course.setDescription("Learn Java basics");

        courseDTO = new CourseDTO();
        courseDTO.setId(1L);
        courseDTO.setTitle("Java Programming");
        courseDTO.setDescription("Learn Java basics");
    }

    @Test
    public void testGetAllCourses() {
        when(courseRepository.findAll()).thenReturn(Arrays.asList(course));

        List<CourseDTO> result = courseService.getAllCourses();

        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(courseDTO.getTitle(), result.get(0).getTitle());
    }

    @Test
    public void testGetCourseById() {
        when(courseRepository.findById(1L)).thenReturn(Optional.of(course));

        CourseDTO result = courseService.getCourseById(1L);

        assertNotNull(result);
        assertEquals(courseDTO.getId(), result.getId());
        assertEquals(courseDTO.getTitle(), result.getTitle());
    }

    @Test
    public void testCreateCourse() {
        when(courseRepository.existsByTitle(any())).thenReturn(false);
        when(courseRepository.save(any(Course.class))).thenReturn(course);

        CourseDTO result = courseService.createCourse(courseDTO);

        assertNotNull(result);
        assertEquals(courseDTO.getTitle(), result.getTitle());
    }
} 