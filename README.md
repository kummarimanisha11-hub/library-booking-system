Library Booking System – Frontend

This is the frontend application for the Library Booking System, developed using Angular 22.

The frontend communicates with the Spring Boot REST API backend to provide user authentication, book management, and library booking functionality.

Features

User Registration

User Login

JWT-based Authentication

JWT Token handling using HTTP Interceptor

View available books

Book a library book

View booking/reservation history

Admin book management

Add new books as Admin

Role-based access for User and Admin

REST API integration with Spring Boot backend

Technologies Used

Angular 22

TypeScript

HTML

CSS

Angular Router

Angular HttpClient

JWT Authentication

Spring Boot REST API

Project Structure

frontend/
├── src/
│   └── app/
│       ├── pages/
│       │   ├── login/
│       │   ├── register/
│       │   ├── books/
│       │   ├── booking/
│       │   └── admin-books/
│       │
│       ├── services/
│       │   ├── auth.ts
│       │   ├── auth.interceptor.ts
│       │   ├── book.service.ts
│       │   └── booking.service.ts
│       │
│       ├── app.config.ts
│       ├── app.routes.ts
│       └── app.ts
│
├── public/
├── package.json
├── angular.json
└── README.md

Application Flow

User
↓
Register / Login
↓
JWT Token
↓
Angular HTTP Interceptor
↓
Spring Boot REST API
↓
MySQL Database

User Features

A normal user can:

Register an account.

Login using email and password.

Receive a JWT token after successful login.

View available books.

Book an available book.

View their booking history.

Admin Features

An Admin can:

Login using Admin credentials.

Access Admin Book Management.

View all books.

Add new books.

Manage books using secured REST APIs.

Authentication

The application uses JWT (JSON Web Token) for authentication.

After successful login, the JWT token is stored in the browser's localStorage.

The Angular HTTP Interceptor automatically adds the token to API requests:

Authorization: Bearer <JWT_TOKEN>

The Spring Boot backend validates the token and checks the user's role.

Backend API

The Angular frontend communicates with the Spring Boot backend running on:

http://localhost:8080

Example API endpoints:

GET    /api/books
POST   /api/books
POST   /api/bookings
GET    /api/bookings/user/{userId}
POST   /api/auth/login
POST   /api/auth/register

Running the Project

1. Install dependencies

npm install

2. Start the Angular development server

ng serve

Open the application in your browser:

http://localhost:4200/

The application automatically reloads when source files are modified.

Build

To create a production build:

ng build

The build files are generated inside the dist/ directory.

Testing

To run unit tests:

ng test

Backend Requirement

Before using the frontend, make sure the Spring Boot backend is running on:

http://localhost:8080

The backend connects to the MySQL database and provides the REST APIs used by this Angular application.

Author

Maneesha Kummari

Library Booking System – Angular Frontend