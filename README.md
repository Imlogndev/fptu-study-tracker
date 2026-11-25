# FPTU Study & Multi-Course Tracker (`fptu-study-tracker`)

A full-stack **Spring Boot 3** web application built for **FPT University Software Engineering students** to manage multiple courses across semesters, track study tasks, log daily DSA/lab practice sessions, and calculate GPA.

> **Live Demo:** *(Deploy via Render using the included `Dockerfile` & `render.yaml` — paste your live URL here once deployed!)*  
> **API Docs:** `/swagger-ui.html`

---

## 🛠️ Tech Stack
- **Backend:** Java 17, Spring Boot 3, Spring Web, Spring Data JPA, Bean Validation
- **Database:** PostgreSQL (Production via `SPRING_DATASOURCE_URL`) / H2 (Local & CI)
- **Frontend:** Responsive HTML5, CSS3, Vanilla JavaScript (`src/main/resources/static/index.html`)
- **API Documentation:** Springdoc OpenAPI (Swagger UI)
- **DevOps & Testing:** JUnit 5, MockMvc, GitHub Actions CI, Multi-stage `Dockerfile`, `render.yaml`

---

## ✨ Core Features
- **Multi-Course & Semester Management:** Filter courses by semester (`PRO192`, `DBI202`, `LAB211`, `CSD201`, `PRJ301`, `SWR302`, `JPD123`, `IOT102`, etc.) and add/update courses with scores.
- **Task & Deadline Tracking:** Attach prioritized tasks (`HIGH`, `MEDIUM`, `LOW`) to any course and toggle completion.
- **Practice Log:** Record daily study/LeetCode minutes per course.
- **Live GPA & Summary Dashboard:** Automatically calculates weighted GPA across completed courses.

---

## 📡 REST API Endpoints
| Method | Endpoint | Description |
| :--- | :--- | :--- |
| `GET` | `/api/health` | Health check for cloud hosting |
| `GET` | `/api/summary` | Weighted GPA, pending tasks, total practice minutes |
| `GET` | `/api/courses?semester=4` | List all courses (optional semester filter) |
| `POST` | `/api/courses` | Create or update a course |
| `GET` | `/api/tasks` | List all study tasks |
| `POST` | `/api/tasks` | Add a task to an existing course |
| `PATCH` | `/api/tasks/{id}/toggle` | Toggle task completion |
| `GET` | `/api/practice-logs` | List practice logs |
| `POST` | `/api/practice-logs` | Log a study/DSA practice session |

---

## 🚀 Deploy to Cloud (Render.com Free Tier)
1. Push this repository to GitHub (`Imlogndev/fptu-study-tracker`).
2. Log in to [Render](https://render.com) with GitHub -> **New +** -> **Web Service** -> select `fptu-study-tracker`.
3. Render automatically detects `Dockerfile` (or use **Blueprints** with `render.yaml`).
4. *(Optional for persistent data)* Create a free PostgreSQL database on Render/Neon and set `SPRING_DATASOURCE_URL`, `SPRING_DATASOURCE_USERNAME`, `SPRING_DATASOURCE_PASSWORD` in Environment Variables.

---

## 💻 Run Locally (Java 17 + Maven)
```powershell
mvn spring-boot:run
```
Then open `http://localhost:8080` (Dashboard) or `http://localhost:8080/swagger-ui.html` (Swagger UI).
