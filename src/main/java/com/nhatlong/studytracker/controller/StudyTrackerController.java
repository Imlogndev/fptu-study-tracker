package com.nhatlong.studytracker.controller;

import com.nhatlong.studytracker.entity.Course;
import com.nhatlong.studytracker.entity.PracticeLog;
import com.nhatlong.studytracker.entity.StudyTask;
import com.nhatlong.studytracker.service.StudyTrackerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api")
@Tag(name = "Study Tracker API", description = "Manage FPTU courses, study tasks, practice logs, and GPA summary")
public class StudyTrackerController {
    private final StudyTrackerService service;

    public StudyTrackerController(StudyTrackerService service) {
        this.service = service;
    }

    @GetMapping("/health")
    @Operation(summary = "Health check endpoint for cloud deployment")
    public Map<String, String> health() {
        return Map.of("status", "UP", "app", "fptu-study-tracker");
    }

    @GetMapping("/summary")
    @Operation(summary = "Get GPA, pending tasks, and total practice minutes")
    public Map<String, Object> summary() {
        return service.getSummary();
    }

    @GetMapping("/courses")
    @Operation(summary = "List all courses or filter by semester")
    public List<Course> getCourses(@RequestParam(required = false) Integer semester) {
        return service.getCourses(semester);
    }

    @PostMapping("/courses")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Create or update a course")
    public Course saveCourse(@Valid @RequestBody Course course) {
        return service.createOrUpdateCourse(course);
    }

    @GetMapping("/tasks")
    @Operation(summary = "List tasks or filter by course code")
    public List<StudyTask> getTasks(@RequestParam(required = false) String courseCode) {
        return service.getTasks(courseCode);
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Add a study task to a course")
    public StudyTask addTask(@Valid @RequestBody StudyTask task) {
        return service.addTask(task);
    }

    @PatchMapping("/tasks/{id}/toggle")
    @Operation(summary = "Toggle task completion status")
    public StudyTask toggleTask(@PathVariable Long id) {
        return service.toggleTask(id);
    }

    @GetMapping("/practice-logs")
    @Operation(summary = "List practice logs or filter by course code")
    public List<PracticeLog> getPracticeLogs(@RequestParam(required = false) String courseCode) {
        return service.getPracticeLogs(courseCode);
    }

    @PostMapping("/practice-logs")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "Log a study or DSA practice session")
    public PracticeLog logPractice(@Valid @RequestBody PracticeLog log) {
        return service.logPractice(log);
    }
}
