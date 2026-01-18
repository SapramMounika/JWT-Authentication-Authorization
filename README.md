### JWT Authentication & Authorization – Project Documentation

#### 1. Overview

This project implements stateless authentication and authorization using JWT (JSON Web Token) in a Spring Boot application.

###### The goal is to:

* Authenticate users using username & password

* Generate a JWT token on successful login

* Protect APIs so only authenticated users can access them

* Avoid server-side sessions

#### 2. Technology Stack

* Java 17

* Spring Boot

* Spring Security

* Spring Data JPA

* PostgreSQL

* JWT (jjwt library)

* Maven

#### 3. Core Concepts
##### Authentication

* Authentication verifies who the user is.

* Implemented via /auth/login

* Uses database credentials

* Returns a JWT token

##### Authorization

* Authorization verifies what the user can access.

* Implemented using JWT token

* Token must be sent with each protected request

#### 4. Project Structure

com.example.jwtsecurity

├── config        → Spring Security & app configuration 

├── controller    → REST controllers (auth & protected APIs)

├── dto           → Request & response DTOs

├── entity        → JPA entities

├── repository    → Database repositories

├── service       → Business logic

├── filter        → JWT filter

├── util          → JWT utility class

#### 5. Database Design

users table
Column	          Type	        Description
id	              bigint	    Primary key
username	      varchar	    Unique username
password	      varchar	    User password
role	          varchar	    User role

#### 6. Authentication Flow (Login)

1. Client sends POST /auth/login

2. Request body contains username & password

3. Controller forwards request to service

4. Service validates user against database

5. JWT token is generated

6. Token is returned to client

##### Login Endpoint
POST /auth/login
##### Request Body
{
  "username": "user",
  "password": "password"
}
##### Response
{
  "token": "<JWT_TOKEN>"
}
#### 7. JWT Token Details

##### JWT contains:

* Subject (username)

* Custom claims (role)

* Issued time

* Expiration time

* Signature

JWT is signed using a secret key.

#### 8. Authorization Flow (Protected APIs)

1. Client calls /api/**

2. JWT token is sent in header

3. JwtFilter intercepts request

4. Token is validated

5. Authentication is set in Spring Security

6. Request is allowed to controller

##### Authorization Header Format
Authorization: Bearer <JWT_TOKEN>

#### 9. JwtFilter Responsibilities

* Executes before controllers

* Skips /auth/** endpoints

* Reads Authorization header

* Extracts & validates JWT

* Sets authentication in SecurityContext

#### 10. Spring Security Configuration

##### Key rules:

* Stateless session management

* CSRF disabled

* /auth/** → public

* /api/** → protected

* JwtFilter added to filter chain

#### 11. Protected API Example
GET /api/hello
##### Without Token

Response: 401 / 403

##### With Token
Hello, JWT Protected API!

#### 12. Error Handling Behavior


| Scenario | Result |
| :---: | :---: |
|Invalid credentials| Login failure |
|Missing token|	401 Unauthorized|
|Invalid token|	401 Unauthorized|
|Valid token|	Access granted|

#### 13. Security Benefits

* Stateless authentication

* Scales easily

* No session storage

* Secure API access

#### 14. Future Enhancements

* Password encryption (BCrypt)

* Role-based authorization

* Refresh tokens

* Global exception handling

#### 15. Summary

This project demonstrates a clean and secure JWT-based authentication & authorization mechanism using Spring Boot. Login generates a token, and protected APIs validate the token using a custom security filter.

#### 16. How to Run

1. Configure PostgreSQL in application.properties

2. Start Spring Boot application

3. Login via /auth/login

4. Use token to access /api/**

#### 17. Maintainer Notes

* JWT filter must skip auth endpoints

* Tokens must be sent in Authorization header

* Stateless security is enforced