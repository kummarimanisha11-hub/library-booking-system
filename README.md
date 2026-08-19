 # Library Booking System

A full-stack Library Booking System developed using Angular, Spring Boot, Spring Security, JWT Authentication, and MySQL.

The application allows users to register, login securely, view available books, book books, and view their reservation history. Administrators can manage books and bookings using secured APIs.

# ✨ Features


## 👤 User Features

- User Registration
- User Login
- JWT-based Authentication
- Secure password encryption using BCrypt
- View available books
- Book an available book
- View booking history
- Return books
- Role-based access

## 👨‍💼 Admin Features

- Admin Login
- View all books
- Add new books
- Update books
- Delete books
- View all bookings
- Role-based authorization

# 🛠️ Technologies Used

## Frontend

- Angular 22
- TypeScript
- HTML5
- CSS3
- Angular Router
- Angular HttpClient
- HTTP Interceptor

## Backend

- Java
- Spring Boot
- Spring MVC
- Spring Data JPA
- Hibernate
- Spring Security
- JWT
- BCrypt
- Maven

## Database

- MySQL

## Tools

- IntelliJ IDEA
- Visual Studio Code
- Postman
- Git
- GitHub

# Architecture

The backend follows a layered architecture:

Controller → Service → Repository → Entity → MySQL

- Controller: Handles HTTP requests and API responses.
- Service: Contains the business logic.
- Repository: Performs database operations using Spring Data JPA.
- Entity: Represents database tables.
- MySQL: Stores application data.

# Authentication

The application uses JWT for secure authentication.

## Login Flow:

User Login → Spring Boot → Password Verification → JWT Token → Angular HTTP Interceptor → Spring Security → Protected API

After successful login, the JWT token is stored in the browser's localStorage.

The Angular HTTP Interceptor automatically adds the token to secured API requests.

## Authorization header:

Authorization: Bearer <JWT_TOKEN>

The Spring Boot backend validates the JWT token and checks the user's role before allowing access to protected APIs.

# Booking Flow

User Login → View Available Books → Select Book → Create Booking → Check Availability → Save Booking → Update Book Availability

When a book is successfully booked:

- Booking status becomes "BOOKED".
- Book availability becomes "false".

When a book is returned:

- Booking status becomes "RETURNED".
- Book availability becomes "true".

# 🔗 Main API Endpoints

## Authentication

| Method | Endpoint | Description |
|---|---|---|
| POST | `/api/auth/register` | Register a new user |
| POST | `/api/auth/login` | Login and generate JWT |

## Books

| Method | Endpoint | Access |
|---|---|---|
| GET | `/api/books` | Public |
| POST | `/api/books` | Admin |
| PUT | `/api/books/{id}` | Admin |
| DELETE | `/api/books/{id}` | Admin |

## Bookings

| Method | Endpoint | Access |
|---|---|---|
| POST | `/api/bookings` | Authenticated |
| GET | `/api/bookings` | Admin |
| GET | `/api/bookings/{id}` | Authenticated |
| GET | `/api/bookings/user/{userId}` | Authenticated |
| PUT | `/api/bookings/{id}` | Authenticated |
| DELETE | `/api/bookings/{id}` | Authenticated |

# Project Structure

## library-booking-system/

├── src/

│   └── main/

│       └── java/

│           └── com/example/librarybookingsystem/

│               ├── config/

│               ├── controller/

│               ├── entity/

│               ├── repository/

│               ├── security/

│               └── service/

│

├── frontend/

│   ├── src/

│   │   └── app/

│   │       ├── pages/

│   │       │   ├── login/

│   │       │   ├── register/

│   │       │   ├── books/

│   │       │   ├── booking/

│   │       │   └── admin-books/

│   │       │

│   │       └── services/

│   │           ├── auth.ts

│   │           ├── auth.interceptor.ts

│   │           ├── book.service.ts

│   │           └── booking.service.ts

│   │

│   ├── package.json

│   └── angular.json

│

├── pom.xml

└── README.md

# How to Run

## Backend

Make sure MySQL is running.

Open the project in IntelliJ IDEA and run the Spring Boot application.

Backend URL:

http://localhost:8080

## Frontend

Open a terminal inside the frontend folder.

Install dependencies:

npm install

Start the Angular development server:

ng serve

Open the application:

http://localhost:4200

# Testing

The REST APIs were tested using Postman.

The following functionalities were tested:

- User Registration
- User Login
- JWT Authentication
- Book APIs
- Booking APIs
- Book Availability
- Booking History
- Return Book
- Admin Book Management
- Role-based Authorization

# Database

The application uses MySQL as the database.

Main entities:

- User
- Book
- Booking

Spring Data JPA and Hibernate are used for database operations.

# Project Objective

The objective of this project is to provide a secure and user-friendly digital library system where users can reserve books and manage their reservations, while administrators can securely manage library resources.

# Author

Maneesha Kummari

B.Tech – Computer Science & Engineering
