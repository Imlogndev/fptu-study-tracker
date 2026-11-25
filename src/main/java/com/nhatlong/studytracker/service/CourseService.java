package com.nhatlong.studytracker.service;

import com.nhatlong.studytracker.model.Course;
import com.nhatlong.studytracker.model.PracticeLog;
import com.nhatlong.studytracker.model.StudyTask;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class CourseService {
    private final Map<String, Course> courses = new LinkedHashMap<>();
    private final List<StudyTask> tasks = new ArrayList<>();
    private final List<PracticeLog> practiceLogs = new ArrayList<>();
    private int nextTaskId = 1;
    private int nextLogId = 1;

    public void addCourse(Course course) {
        if (course.getCredits() <= 0) {
            throw new IllegalArgumentException("Credits must be positive");
        }
        if (courses.containsKey(course.getCode())) {
            throw new IllegalArgumentException("Course already exists: " + course.getCode());
        }
        courses.put(course.getCode(), course);
    }

    public List<Course> getAllCourses() {
        return new ArrayList<>(courses.values());
    }

    public List<Course> getCoursesBySemester(int semester) {
        List<Course> result = new ArrayList<>();
        for (Course c : courses.values()) {
            if (c.getSemester() == semester) {
                result.add(c);
            }
        }
        return result;
    }

    public StudyTask addTask(String courseCode, String title, StudyTask.Priority priority) {
        String key = requireCourseCode(courseCode);
        StudyTask task = new StudyTask(nextTaskId++, key, title, priority);
        tasks.add(task);
        return task;
    }

    public List<StudyTask> getTasksByCourse(String courseCode) {
        String key = courseCode.toUpperCase();
        List<StudyTask> result = new ArrayList<>();
        for (StudyTask t : tasks) {
            if (t.getCourseCode().equals(key)) {
                result.add(t);
            }
        }
        return result;
    }

    public boolean markTaskDone(int taskId) {
        for (StudyTask t : tasks) {
            if (t.getId() == taskId) {
                t.markDone();
                return true;
            }
        }
        return false;
    }

    public PracticeLog logPractice(String courseCode, String topic, int minutes) {
        String key = requireCourseCode(courseCode);
        if (minutes <= 0) {
            throw new IllegalArgumentException("Practice minutes must be positive");
        }
        PracticeLog log = new PracticeLog(nextLogId++, key, topic, minutes, LocalDate.now());
        practiceLogs.add(log);
        return log;
    }

    public List<PracticeLog> getPracticeLogsByCourse(String courseCode) {
        String key = courseCode.toUpperCase();
        List<PracticeLog> result = new ArrayList<>();
        for (PracticeLog log : practiceLogs) {
            if (log.getCourseCode().equals(key)) {
                result.add(log);
            }
        }
        return result;
    }

    public int getTotalPracticeMinutes(String courseCode) {
        String key = courseCode.toUpperCase();
        int total = 0;
        for (PracticeLog log : practiceLogs) {
            if (log.getCourseCode().equals(key)) {
                total += log.getMinutes();
            }
        }
        return total;
    }

    public double calculateCompletedGpa() {
        double totalWeighted = 0.0;
        int totalCredits = 0;
        for (Course c : courses.values()) {
            if (c.getStatus() == Course.Status.COMPLETED && c.getScore() != null) {
                totalWeighted += c.getScore() * c.getCredits();
                totalCredits += c.getCredits();
            }
        }
        return totalCredits == 0 ? 0.0 : totalWeighted / totalCredits;
    }

    private String requireCourseCode(String courseCode) {
        String key = courseCode.toUpperCase();
        if (!courses.containsKey(key)) {
            throw new IllegalArgumentException("Unknown course code: " + key);
        }
        return key;
    }
}
