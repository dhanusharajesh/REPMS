# REPMS – Real Estate Property Management System

A full-stack **Real Estate Property Management System (REPMS)** developed as part of my internship project. The application streamlines property management by providing dedicated dashboards for **Administrators**, **Property Sellers**, and **Buyers/Tenants**, enabling seamless property listing, booking, lease management, maintenance tracking, and payment management.

The project was initially developed using **Java Servlets, JSP, HTML, CSS, JavaScript, JDBC, and MySQL**, and was later enhanced by migrating the frontend to **React** while retaining the existing Java Servlet backend.

---

# Project Overview

REPMS is designed to simplify the management of residential properties by providing a centralized platform for property owners and tenants.

The system allows:

- Property owners to list and manage their properties.
- Buyers/Tenants to browse, book, and rent available properties.
- Administrators to oversee property approvals, booking requests, maintenance requests, and overall system activities.

The application follows a layered architecture using the **DAO Design Pattern**, ensuring clean separation between the presentation layer, business logic, and database interaction.

---

# Features

## Seller Module

- Seller Dashboard
- Seller Profile
- View listed properties
- Track booking requests
- Approve or reject tenant requests
- View payment information
- View maintenance requests
- Monitor rental status

---

## Buyer / Tenant Module

- Buyer Dashboard
- Browse available properties
- Property Booking
- Lease Request Submission
- View My Properties
- Payment Tracking
- Raise Maintenance Requests
- View Request Status
- Buyer Profile Management

---

## Property Management

- Add Property
- Update Property
- Delete Property
- Property Status Management
- Available/Rented Tracking

---

##  Lease Management

- Lease Request Submission
- Booking Approval Workflow
- Property Allocation
- Lease Termination Handling
- Automatic Property Status Updates

---

##  Payment Management

- Rent Payment Tracking
- Payment History
- Seller Payment View
- Buyer Payment View

---

##  Maintenance Module

- Raise Maintenance Requests
- View Maintenance Status
- Complete Maintenance Requests
- Track Request Progress

---

#  Technology Stack

## Backend

- Java
- Java Servlets
- JSP
- JDBC
- Maven

## Frontend

### Initial Version

- HTML5
- CSS3
- JavaScript

### Final Version

- React
- Vite
- JSX

## Database

- MySQL

## IDE

- IntelliJ IDEA

## Build Tool

- Maven

## Version Control

- Git
- GitHub

---

#  Project Architecture

The project follows a layered architecture.

```
REPMS
│
├── src
│   ├── main
│   │
│   ├── java
│   │   ├── controller
│   │   ├── dao
│   │   ├── entity
│   │   ├── exception
│   │   ├── filter
│   │   ├── util
│   │   └── app
│   │
│   ├── resources
│   │
│   └── webapp
│
├── repms-react
│   ├── src
│   ├── public
│   └── components
│
├── frontend
│
└── pom.xml
```

---

#  Backend Components

## Controller Layer

Handles all incoming requests.

Examples:

- LoginServlet
- BookingServlet
- BuyerDashboardServlet
- SellerDashboardServlet
- MaintenanceRequestServlet
- PropertyListServlet
- AdminDashboardServlet

---

## DAO Layer

Responsible for all database operations.

- PropertyService
- PropertyServiceImpl

Uses JDBC with Prepared Statements for secure database interaction.

---

## Entity Layer

Contains Java model classes.

Examples:

- Property
- Owner
- Tenant
- User
- Payment
- BookingRequest
- LeaseRequest
- MaintenanceRequest

---

## Utility Layer

Contains reusable helper classes.

- DBConnUtil
- DBPropertyUtil

---

## Exception Layer

Custom exception handling.

- PropertyNotFoundException
- OwnerNotFoundException
- TenantNotFoundException
- PaymentProcessingException
- MaintenanceRequestException

---

# Database

The application uses **MySQL** as its relational database.

Major entities include:

- Users
- Owners
- Properties
- Tenants
- Booking Requests
- Lease Requests
- Payments
- Maintenance Requests

Relationships are maintained using foreign keys to ensure data integrity.

---

#  Project Workflow

## Property Registration

Seller → Adds Property

↓

Property stored in database

↓

Visible to Buyers

---

## Booking Workflow

Buyer

↓

Select Property

↓

Submit Booking Request

↓

Seller Reviews Request

↓

Approve / Reject

↓

Property Status Updated

---

## Maintenance Workflow

Tenant

↓

Raise Request

↓

Seller Views Request

↓

Complete Maintenance

↓

Status Updated

---

## Payment Workflow

Tenant

↓

Rent Payment

↓

Payment Recorded

↓

Visible to Seller

---

#  React Migration

The project initially used **JSP, HTML, CSS, and JavaScript** for the frontend.

During development, the UI was migrated to **React** to create a more modern and modular user interface while continuing to use the existing Java Servlet backend.

The React application communicates with the backend using HTTP requests, allowing backend business logic and database connectivity to remain unchanged.

---

#  Authentication

- Login System
- Session Management
- Logout Functionality
- User-based Dashboard Navigation

---

#  Screenshots

# 📸 Application Screenshots & Workflows

The following screenshots demonstrate the major workflows implemented in the application.

---

## 🏠 Login Page

Users can securely log in as either a Buyer or Seller to access their respective dashboards.

---

## 👤 Buyer Workflow

The buyer module supports the complete property rental workflow.

### Features

- Buyer Dashboard
- Browse Available Properties
- Submit Booking Request
- View Booking Status
- View My Properties
- Track Lease Requests

**Workflow**

```
Login
      ↓
Buyer Dashboard
      ↓
Browse Available Properties
      ↓
Submit Booking Request
      ↓
Seller Approval
      ↓
Property Allocated
```

---

## 🏢 Seller Workflow

The seller module enables property owners to manage their listings and tenant interactions.

### Features

- Seller Dashboard
- Property Management
- Booking Request Approval
- Maintenance Tracking
- Payment Monitoring

**Workflow**

```
Login
      ↓
Seller Dashboard
      ↓
Manage Properties
      ↓
Review Booking Requests
      ↓
Approve / Reject
      ↓
Manage Maintenance
```

---

## 🔄 Lease Termination Workflow

The system supports tenant lease termination requests.

### Workflow

```
Tenant
      ↓
Submit Lease Termination Request
      ↓
Seller Reviews Request
      ↓
Approve / Reject
      ↓
Property Status Updated
```

---

## ✏️ Lease Update Workflow

Tenants can request updates to their lease information.

Examples include:

- Email Updates
- Phone Number Updates
- Lease Modifications

### Workflow

```
Tenant
      ↓
Submit Update Request
      ↓
Seller Reviews Request
      ↓
Approve / Reject
      ↓
Updated Details Saved
```

---

## 🛠️ Maintenance Management

The maintenance module enables efficient issue tracking between tenants and sellers.

### Features

- Raise Maintenance Requests
- View Maintenance Status
- Complete Maintenance Tasks
- Track Request Progress

---

## 📊 Dashboard Overview

The project includes dedicated dashboards for:

- Administrator
- Seller
- Buyer

Each dashboard provides role-specific statistics, recent activity, quick actions, and workflow management.

#  Installation

## Clone Repository

```bash
git clone https://github.com/dhanusharajesh/REPMS.git
```

---

## Backend Setup

Open the project using IntelliJ IDEA.

Configure the MySQL database.

Update:

```
src/main/resources/db.properties
```

Example:

```
db.url=jdbc:mysql://localhost:3306/repms
db.username=YOUR_USERNAME
db.password=YOUR_PASSWORD
```

Build using Maven.

Deploy the project on Apache Tomcat.

---

## React Frontend

Navigate to:

```
repms-react
```

Install dependencies

```bash
npm install
```

Run

```bash
npm run dev
```

---

#  Future Enhancements

- REST API integration
- Spring Boot migration
- JWT Authentication
- Email Notifications
- Online Payment Gateway
- Property Image Upload
- Google Maps Integration
- Advanced Search & Filtering
- Role-Based Access Control
- Docker Deployment
- Cloud Hosting

---

# 📚 Learning Outcomes

This project strengthened my understanding of:

- Object-Oriented Programming
- Java Servlets
- JSP
- JDBC
- DAO Design Pattern
- MVC Architecture
- React Development
- MySQL Database Design
- Git & GitHub
- REST-style frontend/backend integration
- Session Management
- CRUD Operations
- Exception Handling
- Full-Stack Application Development

---

#  Author

**Dhanusha Rajesh**

B.Tech Computer Science Engineering

GitHub: https://github.com/dhanusharajesh

---

#  Acknowledgement

This project was developed during my internship as a learning-focused full-stack application, providing hands-on experience in designing, developing, and integrating modern web technologies with enterprise Java backend development.