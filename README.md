# Student Course Enrollment System

A simple web-based system for student course enrollment built with Spring Boot.

## Features

- Admin features:
  - Create/Edit/Delete/View students
  - Create/Edit/Delete/View courses
  - Create/Edit/Delete/View enrollments
  - Filter and view data (courses enrolled by a student, students enrolled in a course)

- Student features:
  - View all courses
  - Enroll in desired courses
  - View personal enrollments

## Technologies Used

- Backend: Java 17, Spring Boot 3.2.3
- Database: PostgreSQL
- Security: Basic Authentication
- Frontend: Simple HTML

## Getting Started

### Prerequisites

- JDK 17 or later
- PostgreSQL
- Gradle

### Database Setup

1. Create a PostgreSQL database named `studentenrollment`
2. Configure the database connection in `src/main/resources/application.properties`

### Running the Application

```bash
# Clone the repository
git clone https://github.com/yourusername/student-enrollment.git
cd student-enrollment

# Build the project
./gradlew build

# Run the application
./gradlew bootRun
```

The application will start at http://localhost:8080

## Default Admin Account

- Email: admin@example.com
- Password: admin
