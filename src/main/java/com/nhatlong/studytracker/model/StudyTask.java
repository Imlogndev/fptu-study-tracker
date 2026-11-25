package com.nhatlong.studytracker.model;

public class StudyTask {
    public enum Priority {
        LOW, MEDIUM, HIGH
    }

    private final int id;
    private final String courseCode;
    private final String title;
    private final Priority priority;
    private boolean done;

    public StudyTask(int id, String courseCode, String title, Priority priority) {
        this.id = id;
        this.courseCode = courseCode.toUpperCase();
        this.title = title;
        this.priority = priority;
        this.done = false;
    }

    public int getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTitle() {
        return title;
    }

    public Priority getPriority() {
        return priority;
    }

    public boolean isDone() {
        return done;
    }

    public void markDone() {
        this.done = true;
    }

    @Override
    public String toString() {
        String check = done ? "[x]" : "[ ]";
        return String.format("#%d %s [%-6s] (%s) %s", id, check, priority, courseCode, title);
    }
}
