package com.studentapp.config;

import com.studentapp.model.Course;
import com.studentapp.model.Enrollment;
import com.studentapp.model.Student;
import com.studentapp.repository.CourseRepository;
import com.studentapp.repository.EnrollmentRepository;
import com.studentapp.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    private final StudentRepository studentRepository;
    private final CourseRepository courseRepository;
    private final EnrollmentRepository enrollmentRepository;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public DataInitializer(StudentRepository studentRepository,
                          CourseRepository courseRepository,
                          EnrollmentRepository enrollmentRepository,
                          PasswordEncoder passwordEncoder) {
        this.studentRepository = studentRepository;
        this.courseRepository = courseRepository;
        this.enrollmentRepository = enrollmentRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        // Only initialize if the database is empty
        if (studentRepository.count() == 0 && courseRepository.count() == 0) {
            // Create admin account
            Student admin = new Student();
            admin.setName("Admin User");
            admin.setEmail("admin@example.com");
            admin.setPassword(passwordEncoder.encode("admin"));
            admin.setRole("ADMIN");
            studentRepository.save(admin);

            // Create a student account
            Student student = new Student();
            student.setName("Student");
            student.setEmail("student@example.com");
            student.setPassword(passwordEncoder.encode("password"));
            student.setRole("STUDENT");
            studentRepository.save(student);

            Course java = new Course();
            java.setTitle("Java Programming");
            java.setDescription("Learn Java programming language fundamentals");
            courseRepository.save(java);

            Course spring = new Course();
            spring.setTitle("Spring Framework");
            spring.setDescription("Learn Spring Framework for Java development");
            courseRepository.save(spring);

            Course database = new Course();
            database.setTitle("Database Design");
            database.setDescription("Learn database design principles and SQL");
            courseRepository.save(database);

            // Create an enrollment
            Enrollment enrollment = new Enrollment();
            enrollment.setStudent(student);
            enrollment.setCourse(java);
            enrollmentRepository.save(enrollment);

            System.out.println("Sample data initialized!");
        }
    }
} 