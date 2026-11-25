package com.nhatlong.studytracker.model;

public class Course {
    public enum Status {
        PLANNED, IN_PROGRESS, COMPLETED
    }

    private final String code;
    private final String name;
    private final int semester;
    private final int credits;
    private Status status;
    private Double score;

    public Course(String code, String name, int semester, int credits, Status status, Double score) {
        this.code = code.toUpperCase();
        this.name = name;
        this.semester = semester;
        this.credits = credits;
        this.status = status;
        this.score = score;
    }

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }

    public int getSemester() {
        return semester;
    }

    public int getCredits() {
        return credits;
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

    @Override
    public String toString() {
        String scoreText = (score == null) ? "-" : String.format("%.2f", score);
        return String.format("[Sem %d] %-8s | %-38s | %d cr | %-11s | Score: %s",
                semester, code, name, credits, status, scoreText);
    }
}
