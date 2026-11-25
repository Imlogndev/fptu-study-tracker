# FPTU Study & Multi-Course Tracker (`fptu-study-tracker`)

A personal Java application designed for **FPT University Software Engineering students** to manage multiple courses across semesters, track study tasks/deadlines, log practice sessions (e.g., `CSD201` DSA problems, `PRJ301` labs), and calculate target GPA.

---

## 🎯 Project Evolution Roadmap
- **Phase 1 (Current — Java Core + DBI202):** Clean layered architecture (`model` -> `service` -> `dao`), SQL schema design, Maven structure, and JUnit 5 unit tests.
- **Phase 2 (Semester 4 — `PRJ301` & `SWR302`):** Upgrade to JDBC + SQL Server/PostgreSQL, Servlet/JSP web interface, and full SRS/ERD documentation in `docs/`.
- **Phase 3 (Backend Production — Spring Boot 3):** Migrate to RESTful APIs with Spring Boot, Spring Data JPA, Spring Security (JWT), Docker Compose, and live cloud deployment.

---

## ✨ Core Features (Multi-Course Support)
- **Multi-Semester & Course Management:** Organize courses by semester (`PRO192`, `DBI202`, `CSD201`, `PRJ301`, `SWR302`, `JPD123`, `IOT102`, etc.) with credits and status (`PLANNED`, `IN_PROGRESS`, `COMPLETED`).
- **Task & Deadline Tracking per Course:** Attach assignments, labs, and exam prep tasks to any course.
- **Practice Log:** Track daily study hours and coding exercises (e.g., LeetCode/DSA topics for `CSD201`).
- **GPA Calculator:** Compute weighted GPA across completed courses.

---

## 🗄️ Database Schema
Designed following **DBI202** normalization principles. See [`sql/schema.sql`](sql/schema.sql) for table definitions (`courses`, `study_tasks`, `practice_logs`).

---

## 🚀 How to Run (Java 8+)

Compile and run directly from the project root:

```powershell
javac --release 8 -encoding UTF-8 -d out (Get-ChildItem -Recurse -Filter *.java src\main\java | ForEach-Object { $_.FullName })
java -cp out com.nhatlong.studytracker.Main
```

Or with Maven (when installed):

```powershell
mvn compile exec:java -Dexec.mainClass="com.nhatlong.studytracker.Main"
```
