# Boat Management Application

## 🚤 Project Summary

This is a simple full-stack application for managing a list of boats. The application is built to demonstrate a basic **CRUD (Create, Read, Update, Delete)** system with user authentication and authorization. It provides a quick and intuitive interface for authenticated users to view, add, modify, and delete boat records.

### Functional Requirements Fulfilled:

* **User Authentication**: Users must log in to access the application. Upon successful login, they are redirected to the boat list overview.
* **Boat Management**: The application provides a comprehensive interface for managing boat resources, allowing users to perform CRUD operations.
* **Boat Details**: Each boat has a name and a description, with appropriate validation to ensure data integrity.
* **CRUD Endpoints**: A secure REST API is implemented to handle all interactions with the boat data.

---

## 🛠️ Tech Stack

### Backend

The backend is a **Spring Boot** application built with **Java 21**. It follows a standard layered architecture with a focus on security and RESTful API design.

* **Spring Boot**: The core framework for building the application.
* **Spring Data JPA**: For data persistence and interaction with the H2 database.
* **Spring Security**: Handles user authentication and authorization, ensuring only authenticated users can access the boat resources.
* **Jakarta Validation**: Used for validating boat data (Name and Description).
* **Springdoc OpenAPI UI**: Provides auto-generated API documentation for the REST endpoints.
* **JJWT (JSON Web Token)**: Used to implement token-based authentication.
* **Lombok**: Reduces boilerplate code (e.g., getters, setters, constructors).
* **H2 Database**: An in-memory database used for development and testing.

### Frontend

The frontend is a modern single-page application built with **React** and **TypeScript**.

* **React**: A popular JavaScript library for building user interfaces.
* **Vite**: A fast and lightweight build tool for frontend development.
* **TypeScript**: Adds static typing to JavaScript, improving code quality and developer experience.
* **Axios**: A promise-based HTTP client for making API requests to the backend.
* **React Router**: Manages routing and navigation within the application.

---

## 📁 Project Structure

The project is a monorepo containing both the frontend and backend.
```
boat-app/
├── frontend/
│   ├── ... (Frontend with React + Vite)
├── src/
│   └── ... (Java backend source code)
├── .gitignore
├── Dockerfile
├── docker-compose.yml
├── gradlew
├── gradlew.bat
├── pom.xml
└── README.md
```

---

## 🚀 How to Run the Application

There are two primary ways to run this application:

### Option 1: Using Gradle and Vite

This method involves running the frontend and backend separately.

1.  **Start the Backend (from IntelliJ IDEA):**
    * Open the project in IntelliJ IDEA.
    * Navigate to the main application class (e.g., `BoatAppApplication.java`).
    * Right-click and select **"Run 'BoatAppApplication.main()'"**. The backend will start on `http://localhost:8080`.

2.  **Start the Frontend (from Command Line):**
    * Open a terminal and navigate to the `frontend` directory:
        ```bash
        cd frontend
        ```
    * Install the required dependencies:
        ```bash
        npm install
        ```
    * Start the development server:
        ```bash
        npm run dev
        ```
    * The frontend will be available at `http://localhost:5173`.

### Option 2: Using Docker Compose

This is the recommended approach for a production-like environment, as it simplifies running both services with a single command.

1.  **Build and Run with Docker Compose:**
    * Ensure you have Docker and Docker Compose installed.
    * Open a terminal in the project root directory.
    * Execute the following command to build the Docker images and start the containers:
        ```bash
        docker-compose up --build
        ```
    * The application will be accessible at `http://localhost:5173`. The frontend will serve the React app, which will communicate with the backend running in a separate container.