# Library Booking System – REST API

A backend REST API based Library Booking System developed using Java and Spring Boot.

The system allows users to register, login, browse books, and reserve books. Admins can manage books and reservations.

---

## Objective

To provide a simple and efficient REST API based system for managing library books, users, and reservations.

---

## Key Features

- User Registration
- User Login
- JWT Authentication
- Browse and Search Books
- View Book Details
- Book Reservation
- View Reservation History
- Admin Book Management
- Add Books
- Update Books
- Delete Books
- View All Reservations
- Role-Based Authorization

---

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT (JSON Web Token)
- MySQL
- Maven
- Postman
- IntelliJ IDEA

---

# Authentication APIs

## 1. Register User

**POST** `/api/auth/register`

### Request Body

```json
{
  "name": "Your Name",
  "email": "your@email.com",
  "password": "your_password",
  "role": "USER"
}
```
## login user
**POST** `/api/auth/login`
###Request Body
```json
{
 "email":"your@email.com",
 "password":"your_password"
}
```
### Description



Authenticates the user and generates a JWT token after successful login.
The JWT token is used to access protected APIs.

---


# Book APIs

## 1. Get All Books

**GET** ` /api/books`

### Description
Returns all books available in the library.

## 2. Get Book By ID

**GET** `/api/books/{id}`

### Description

Returns the details of a specific book using its ID.

## 3. Add Book

**POST** `/api/books`

### Description

Adds a new book to the library.

**Admin only**

## 4. Update Book

**PUT** `/api/books/{id}`

### Description

Updates the details of an existing book.

**Admin only**

## 5. Delete Book

**DELETE** `/api/books/{id}`

### Description

Deletes a book from the library.

**Admin only**
---
# Reservation APIs

## Create Reservation

**POST** `/api/reservations`

### Description:

Allows a user to reserve a book.

## User Reservation History

**GET** `/api/users/{id}/reservations`

### Description:

Returns the reservation history of a user.

## All Reservations

**GET** `/api/admin/reservations`

### Description:

Returns all reservations.

**Admin only**

---
# Database

This project uses ## MySQL.

## Database Name: ### library_db

Create the database using:

` CREATE DATABASE library_db;`

Database configuration is available in:

`src/main/resources/application.properties`

Use your local MySQL password in `application.properties`.Actual password ni README/GitHub lo pettaku.
