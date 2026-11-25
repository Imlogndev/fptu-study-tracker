package com.nhatlong.studytracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "study_tasks")
public class StudyTask {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "task_id")
    private Long id;

    @Column(name = "course_code", nullable = false, length = 20)
    @NotBlank(message = "Course code is required")
    private String courseCode;

    @Column(nullable = false, length = 200)
    @NotBlank(message = "Task title is required")
    private String title;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 10)
    private Priority priority = Priority.MEDIUM;

    @Column(name = "is_done", nullable = false)
    private boolean done = false;

    public StudyTask() {
    }

    public StudyTask(String courseCode, String title, Priority priority) {
        this.courseCode = courseCode.trim().toUpperCase();
        this.title = title.trim();
        this.priority = priority != null ? priority : Priority.MEDIUM;
        this.done = false;
    }

    public Long getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public void setCourseCode(String courseCode) {
        this.courseCode = courseCode != null ? courseCode.trim().toUpperCase() : null;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }
}
