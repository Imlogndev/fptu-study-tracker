package com.nhatlong.studytracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

@Entity
@Table(name = "practice_logs")
public class PracticeLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "log_id")
    private Long id;

    @Column(name = "course_code", nullable = false, length = 20)
    @NotBlank(message = "Course code is required")
    private String courseCode;

    @Column(nullable = false, length = 150)
    @NotBlank(message = "Topic is required")
    private String topic;

    @Column(nullable = false)
    @Min(value = 1, message = "Minutes must be at least 1")
    private int minutes;

    @Column(name = "logged_date", nullable = false)
    private LocalDate loggedDate = LocalDate.now();

    public PracticeLog() {
    }

    public PracticeLog(String courseCode, String topic, int minutes, LocalDate loggedDate) {
        this.courseCode = courseCode.trim().toUpperCase();
        this.topic = topic.trim();
        this.minutes = minutes;
        this.loggedDate = loggedDate != null ? loggedDate : LocalDate.now();
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

    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public int getMinutes() {
        return minutes;
    }

    public void setMinutes(int minutes) {
        this.minutes = minutes;
    }

    public LocalDate getLoggedDate() {
        return loggedDate;
    }

    public void setLoggedDate(LocalDate loggedDate) {
        this.loggedDate = loggedDate;
    }
}
