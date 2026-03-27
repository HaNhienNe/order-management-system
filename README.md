# 📦 Order Management System

## 📖 Introduction

Order Management System is a full-stack web application built with Angular (frontend) and Spring Boot (backend).
The system allows users to manage and track the lifecycle of orders in a business environment.

---

## 🚀 Tech Stack

### Backend

* Java, Spring Boot
* Spring Security (JWT Authentication)
* JPA / Hibernate
* RESTful API

### Frontend

* Angular
* TypeScript
* Angular Router, HTTP Client

### Database

* MySQL / PostgreSQL

---

## 🔐 Features

* User authentication and authorization (JWT, Role-based Access Control)
* User management (Admin)
* Order management:

  * Create / Read / Update / Delete orders
  * Search, filter, pagination
  * Order status tracking

---

## 🔄 Order Workflow

CREATED → PROCESSING → SHIPPED → COMPLETED

**Rules:**

* Cannot skip steps
* Cannot update completed orders

---

## 🏗️ System Architecture

### Backend (Layered Architecture)

* Controller: Handle HTTP requests
* Service: Business logic
* Repository: Data access layer

### Frontend

* Component-based architecture
* Route Guard (authentication protection)
* HTTP Interceptor (attach JWT token)

---

## 🗄️ Database Design

### users

* id
* username
* password
* role

### orders

* id
* customer_name
* status
* total_price
* created_at

### order_items

* id
* order_id
* product_name
* quantity
* price

---

## 🔗 API Endpoints

### Auth

* POST /api/auth/login
* POST /api/auth/register

### User

* GET /api/users
* POST /api/users

### Order

* GET /api/orders
* GET /api/orders/{id}
* POST /api/orders
* PUT /api/orders/{id}
* DELETE /api/orders/{id}

---

## ▶️ How to Run

### Backend

```bash
cd backend
mvn spring-boot:run
```

### Frontend

```bash
cd frontend
npm install
npm start
```

---

## 🎯 Project Purpose

* Practice full-stack development with Angular and Spring Boot
* Apply RESTful API design and layered architecture
* Implement authentication, authorization, and business logic

---

## 📌 Notes

This project is built for learning and demonstration purposes, focusing on core functionalities of an order management system.
