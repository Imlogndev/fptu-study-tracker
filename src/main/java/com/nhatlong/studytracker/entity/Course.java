package com.nhatlong.studytracker.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "courses")
public class Course {
    public enum Status {
        PLANNED, IN_PROGRESS, COMPLETED
    }

    @Id
    @Column(name = "course_code", length = 20)
    @NotBlank(message = "Course code is required")
    private String code;

    @Column(name = "course_name", nullable = false, length = 120)
    @NotBlank(message = "Course name is required")
    private String name;

    @Column(nullable = false)
    @Min(1)
    @Max(9)
    private int semester;

    @Column(nullable = false)
    @Min(1)
    private int credits;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, length = 20)
    private Status status = Status.IN_PROGRESS;

    @DecimalMin("0.0")
    @DecimalMax("10.0")
    private Double score;

    public Course() {
    }

    public Course(String code, String name, int semester, int credits, Status status, Double score) {
        this.code = code.trim().toUpperCase();
        this.name = name.trim();
        this.semester = semester;
        this.credits = credits;
        this.status = status != null ? status : Status.IN_PROGRESS;
        this.score = score;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code != null ? code.trim().toUpperCase() : null;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public int getCredits() {
        return credits;
    }

    public void setCredits(int credits) {
        this.credits = credits;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }
}
