-- Database Schema for FPTU Study & Multi-Course Tracker (DBI202 Standard)

CREATE TABLE courses (
    course_code VARCHAR(20) PRIMARY KEY,
    course_name VARCHAR(120) NOT NULL,
    semester    INT NOT NULL CHECK (semester BETWEEN 1 AND 9),
    credits     INT NOT NULL CHECK (credits > 0),
    status      VARCHAR(20) NOT NULL DEFAULT 'IN_PROGRESS', -- PLANNED, IN_PROGRESS, COMPLETED
    score       DECIMAL(4, 2) NULL CHECK (score BETWEEN 0 AND 10)
);

CREATE TABLE study_tasks (
    task_id     INT IDENTITY(1,1) PRIMARY KEY,
    course_code VARCHAR(20) NOT NULL,
    title       VARCHAR(200) NOT NULL,
    priority    VARCHAR(10) NOT NULL DEFAULT 'MEDIUM', -- LOW, MEDIUM, HIGH
    is_done     BIT NOT NULL DEFAULT 0,
    due_date    DATE NULL,
    CONSTRAINT fk_task_course FOREIGN KEY (course_code) REFERENCES courses(course_code)
);

CREATE TABLE practice_logs (
    log_id      INT IDENTITY(1,1) PRIMARY KEY,
    course_code VARCHAR(20) NOT NULL,
    topic       VARCHAR(150) NOT NULL,
    minutes     INT NOT NULL CHECK (minutes > 0),
    logged_at   DATETIME NOT NULL DEFAULT GETDATE(),
    CONSTRAINT fk_log_course FOREIGN KEY (course_code) REFERENCES courses(course_code)
);
