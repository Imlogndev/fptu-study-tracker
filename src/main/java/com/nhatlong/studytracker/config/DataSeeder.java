package com.nhatlong.studytracker.config;

import com.nhatlong.studytracker.entity.Course;
import com.nhatlong.studytracker.entity.PracticeLog;
import com.nhatlong.studytracker.entity.StudyTask;
import com.nhatlong.studytracker.repository.CourseRepository;
import com.nhatlong.studytracker.repository.PracticeLogRepository;
import com.nhatlong.studytracker.repository.StudyTaskRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class DataSeeder implements CommandLineRunner {
    private final CourseRepository courseRepository;
    private final StudyTaskRepository taskRepository;
    private final PracticeLogRepository logRepository;

    public DataSeeder(
            CourseRepository courseRepository,
            StudyTaskRepository taskRepository,
            PracticeLogRepository logRepository) {
        this.courseRepository = courseRepository;
        this.taskRepository = taskRepository;
        this.logRepository = logRepository;
    }

    @Override
    public void run(String... args) {
        if (courseRepository.count() > 0) {
            return;
        }

        courseRepository.saveAll(List.of(
                new Course("PRO192", "Object-Oriented Programming", 2, 3, Course.Status.COMPLETED, 8.2),
                new Course("DBI202", "Introduction to Databases", 3, 3, Course.Status.COMPLETED, 8.5),
                new Course("LAB211", "OOP with Java Lab", 3, 3, Course.Status.COMPLETED, 8.8),
                new Course("CSD201", "Data Structures and Algorithms", 4, 3, Course.Status.IN_PROGRESS, null),
                new Course("PRJ301", "Java Web Application Development", 4, 3, Course.Status.IN_PROGRESS, null),
                new Course("SWR302", "Software Requirement", 4, 3, Course.Status.IN_PROGRESS, null),
                new Course("JPD123", "Elementary Japanese 1-A1.2", 4, 3, Course.Status.IN_PROGRESS, null),
                new Course("IOT102", "Internet of Things", 4, 3, Course.Status.IN_PROGRESS, null)
        ));

        taskRepository.saveAll(List.of(
                new StudyTask("CSD201", "Implement SinglyLinkedList & solve 3 LeetCode Easy", StudyTask.Priority.HIGH),
                new StudyTask("PRJ301", "Design MVC folder structure with Servlet + JDBC", StudyTask.Priority.HIGH),
                new StudyTask("SWR302", "Write Use Case & ERD for Study Tracker", StudyTask.Priority.MEDIUM)
        ));

        logRepository.saveAll(List.of(
                new PracticeLog("CSD201", "Array & HashMap - LeetCode Two Sum", 45, LocalDate.now()),
                new PracticeLog("PRJ301", "HTTP Request/Response & JDBC PreparedStatement", 60, LocalDate.now())
        ));
    }
}
