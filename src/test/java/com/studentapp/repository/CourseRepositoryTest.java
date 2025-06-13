package com.studentapp.repository;

import com.studentapp.model.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DataJpaTest
public class CourseRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private CourseRepository courseRepository;

    @Test
    public void testExistsByTitle() {
        Course course = new Course();
        course.setTitle("Java Programming");
        course.setDescription("Learn Java basics");
        entityManager.persist(course);
        entityManager.flush();

        boolean exists = courseRepository.existsByTitle("Java Programming");
        assertTrue(exists);
    }

    @Test
    public void testSaveCourse() {
        Course course = new Course();
        course.setTitle("Spring Boot");
        course.setDescription("Learn Spring Boot");

        Course savedCourse = courseRepository.save(course);

        assertEquals("Spring Boot", savedCourse.getTitle());
        assertEquals("Learn Spring Boot", savedCourse.getDescription());
    }
} 