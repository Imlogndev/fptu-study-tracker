package com.nhatlong.studytracker.model;

import java.time.LocalDate;

public class PracticeLog {
    private final int id;
    private final String courseCode;
    private final String topic;
    private final int minutes;
    private final LocalDate loggedDate;

    public PracticeLog(int id, String courseCode, String topic, int minutes, LocalDate loggedDate) {
        this.id = id;
        this.courseCode = courseCode.toUpperCase();
        this.topic = topic;
        this.minutes = minutes;
        this.loggedDate = loggedDate;
    }

    public int getId() {
        return id;
    }

    public String getCourseCode() {
        return courseCode;
    }

    public String getTopic() {
        return topic;
    }

    public int getMinutes() {
        return minutes;
    }

    public LocalDate getLoggedDate() {
        return loggedDate;
    }

    @Override
    public String toString() {
        return String.format("#%d [%s] (%s) %s - %d mins", id, loggedDate, courseCode, topic, minutes);
    }
}
