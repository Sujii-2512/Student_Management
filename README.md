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

## API Endpoints

### Authentication

- POST `/api/auth/register` - Register a new student account

### Admin Endpoints

- GET `/api/admin/students` - Get all students
- GET `/api/admin/students/{id}` - Get student by ID
- POST `/api/admin/students` - Create a new student
- PUT `/api/admin/students/{id}` - Update a student
- DELETE `/api/admin/students/{id}` - Delete a student

- GET `/api/admin/courses` - Get all courses
- GET `/api/admin/courses/{id}` - Get course by ID
- POST `/api/admin/courses` - Create a new course
- PUT `/api/admin/courses/{id}` - Update a course
- DELETE `/api/admin/courses/{id}` - Delete a course

- GET `/api/admin/enrollments` - Get all enrollments
- GET `/api/admin/enrollments/student/{studentId}` - Get enrollments by student ID
- GET `/api/admin/enrollments/course/{courseId}` - Get enrollments by course ID
- POST `/api/admin/enrollments` - Create a new enrollment
- DELETE `/api/admin/enrollments/{id}` - Delete an enrollment

### Student Endpoints

- GET `/api/students/courses` - Get all available courses
- GET `/api/students/courses/{id}` - Get course by ID
- POST `/api/students/enroll` - Enroll in a course
- GET `/api/students/enrollments` - Get student's enrollments

## Testing with Postman

1. Import the provided Postman collection
2. Set up Basic Authentication in Postman with your credentials
3. Test the API endpoints

## Default Admin Account

- Email: admin@example.com
- Password: admin
