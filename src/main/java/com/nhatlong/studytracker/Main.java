package com.nhatlong.studytracker;

import com.nhatlong.studytracker.model.Course;
import com.nhatlong.studytracker.model.PracticeLog;
import com.nhatlong.studytracker.model.StudyTask;
import com.nhatlong.studytracker.service.CourseService;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        CourseService service = new CourseService();
        seedSampleData(service);

        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== FPTU STUDY & MULTI-COURSE TRACKER ===");
            System.out.println("1. View all courses");
            System.out.println("2. View courses by semester");
            System.out.println("3. Add a study task to a course");
            System.out.println("4. View tasks for a course");
            System.out.println("5. Mark task as done");
            System.out.println("6. Show completed GPA");
            System.out.println("7. Log practice session (DSA / Lab / Revision)");
            System.out.println("8. View practice logs for a course");
            System.out.println("0. Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();
            switch (choice) {
                case "1":
                    for (Course c : service.getAllCourses()) {
                        System.out.println(c);
                    }
                    break;
                case "2":
                    System.out.print("Enter semester (1-9): ");
                    int sem = Integer.parseInt(scanner.nextLine().trim());
                    List<Course> semCourses = service.getCoursesBySemester(sem);
                    if (semCourses.isEmpty()) {
                        System.out.println("No courses found for semester " + sem);
                    } else {
                        for (Course c : semCourses) {
                            System.out.println(c);
                        }
                    }
                    break;
                case "3":
                    System.out.print("Course code (e.g. CSD201): ");
                    String code = scanner.nextLine().trim();
                    System.out.print("Task title: ");
                    String title = scanner.nextLine().trim();
                    System.out.print("Priority (LOW/MEDIUM/HIGH): ");
                    StudyTask.Priority priority = StudyTask.Priority.valueOf(scanner.nextLine().trim().toUpperCase());
                    StudyTask created = service.addTask(code, title, priority);
                    System.out.println("Added: " + created);
                    break;
                case "4":
                    System.out.print("Course code: ");
                    String filterCode = scanner.nextLine().trim();
                    List<StudyTask> courseTasks = service.getTasksByCourse(filterCode);
                    if (courseTasks.isEmpty()) {
                        System.out.println("No tasks for " + filterCode.toUpperCase());
                    } else {
                        for (StudyTask t : courseTasks) {
                            System.out.println(t);
                        }
                    }
                    break;
                case "5":
                    System.out.print("Task ID to mark done: ");
                    int id = Integer.parseInt(scanner.nextLine().trim());
                    System.out.println(service.markTaskDone(id) ? "Updated!" : "Task not found.");
                    break;
                case "6":
                    System.out.printf("Completed GPA (scale 10): %.2f%n", service.calculateCompletedGpa());
                    break;
                case "7":
                    System.out.print("Course code (e.g. CSD201): ");
                    String logCourse = scanner.nextLine().trim();
                    System.out.print("Topic practiced: ");
                    String topic = scanner.nextLine().trim();
                    System.out.print("Minutes spent: ");
                    int mins = Integer.parseInt(scanner.nextLine().trim());
                    PracticeLog log = service.logPractice(logCourse, topic, mins);
                    System.out.println("Logged: " + log);
                    break;
                case "8":
                    System.out.print("Course code: ");
                    String logFilter = scanner.nextLine().trim();
                    List<PracticeLog> logs = service.getPracticeLogsByCourse(logFilter);
                    if (logs.isEmpty()) {
                        System.out.println("No practice logs for " + logFilter.toUpperCase());
                    } else {
                        for (PracticeLog l : logs) {
                            System.out.println(l);
                        }
                        System.out.println("Total minutes: " + service.getTotalPracticeMinutes(logFilter));
                    }
                    break;
                case "0":
                    System.out.println("Keep coding every day! Bye.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    private static void seedSampleData(CourseService service) {
        service.addCourse(new Course("PRO192", "Object-Oriented Programming", 2, 3, Course.Status.COMPLETED, 8.2));
        service.addCourse(new Course("DBI202", "Introduction to Databases", 3, 3, Course.Status.COMPLETED, 8.5));
        service.addCourse(new Course("LAB211", "OOP with Java Lab", 3, 3, Course.Status.COMPLETED, 8.8));

        service.addCourse(new Course("CSD201", "Data Structures and Algorithms", 4, 3, Course.Status.IN_PROGRESS, null));
        service.addCourse(new Course("PRJ301", "Java Web Application Development", 4, 3, Course.Status.IN_PROGRESS, null));
        service.addCourse(new Course("SWR302", "Software Requirement", 4, 3, Course.Status.IN_PROGRESS, null));
        service.addCourse(new Course("JPD123", "Elementary Japanese 1-A1.2", 4, 3, Course.Status.IN_PROGRESS, null));
        service.addCourse(new Course("IOT102", "Internet of Things", 4, 3, Course.Status.IN_PROGRESS, null));

        service.addTask("CSD201", "Implement SinglyLinkedList & solve 3 LeetCode Easy", StudyTask.Priority.HIGH);
        service.addTask("PRJ301", "Design MVC folder structure with Servlet + JDBC", StudyTask.Priority.HIGH);
        service.addTask("SWR302", "Write Use Case & ERD for Study Tracker", StudyTask.Priority.MEDIUM);

        service.logPractice("CSD201", "Array & HashMap - LeetCode Two Sum", 45);
        service.logPractice("PRJ301", "Review HTTP Request/Response & JDBC PreparedStatement", 60);
    }
}
