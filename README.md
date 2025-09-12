# 🚤 BoatApp

BoatApp is a simple full-stack application where users can view and manage boats.  
It demonstrates authentication/authorization, CRUD operations, and a React frontend integrated with a Spring Boot backend.

---

## 📖 Project Structure

```
boat-app/
│── backend/   # Spring Boot (Java 21, Gradle)
│── frontend/  # React + Vite + TypeScript
│── docker-compose.yml
│── README.md
```

---

## ⚙️ Technologies Used

**Backend (Spring Boot)**
- Java 21
- Spring Boot 3
- Spring Web
- Spring Security (JWT-based authentication)
- Spring Data JPA (H2 in-memory database)
- Lombok

**Frontend (React + Vite + TypeScript)**
- React Router (navigation)
- Axios (HTTP requests)
- TailwindCSS (styling)

**Other**
- Docker & Docker Compose
- IntelliJ IDEA / VS Code (development)

---

## 👤 Hardcoded Users

The backend comes with preloaded users in the H2 database:

| Username | Password | Role  |
|----------|----------|-------|
| `user`   | `password` | USER |
| `admin`  | `password` | ADMIN |

⚠️ Passwords are **BCrypt encoded** but stored in plain text here for testing.

---

## 🔑 API Endpoints

### Authentication
- `POST /api/auth/login` → login with username & password, receive JWT token  
- `POST /api/auth/register` → register a new user (role: USER by default)

### Boats
(All require a valid JWT in the `Authorization: Bearer <token>` header)

- `GET /api/boats` → list all boats  
- `GET /api/boats/{id}` → get boat by id  
- `POST /api/boats` → create new boat  
- `PUT /api/boats/{id}` → update boat  
- `DELETE /api/boats/{id}` → delete boat  

### Swagger Docs
- `http://localhost:8080/swagger-ui.html`

---

## 🚀 How to Run the Project

### Option 1: Run Separately (Dev Mode)

#### Backend
1. Open `backend/` in IntelliJ IDEA.  
2. Run `BoatApplication.java`.  
3. Backend available at: `http://localhost:8080`.

#### Frontend
1. Open terminal in `frontend/`.  
2. Install dependencies:
   ```bash
   npm install
   ```
3. Start dev server:
   ```bash
   npm run dev
   ```
4. Frontend available at: `http://localhost:5173`.

The frontend proxies API requests to `http://localhost:8080/api` (configured in `vite.config.ts`).

---

### Option 2: Run with Docker Compose

1. Ensure Docker is installed & running.
2. From project root, run:
   ```bash
   docker compose up --build
   ```
3. Access:
   - Backend → `http://localhost:8080`
   - Frontend → `http://localhost:5173`

---

## 🧪 Trying It Out

1. Start the app (see above).  
2. Login via Postman or curl:
   ```bash
   curl -X POST http://localhost:8080/api/auth/login      -H "Content-Type: application/json"      -d '{"username":"user","password":"password"}'
   ```
3. Copy the JWT token from the response.  
4. Use the token to access boats:
   ```bash
   curl http://localhost:8080/api/boats      -H "Authorization: Bearer <TOKEN>"
   ```

---

## ✅ Next Steps
- Add registration form in frontend.  
- Deploy to cloud (Heroku, Render, or Vercel + Railway).  
- Replace H2 with PostgreSQL in Docker.

---
