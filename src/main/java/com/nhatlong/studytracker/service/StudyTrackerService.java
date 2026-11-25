package com.nhatlong.studytracker.service;

import com.nhatlong.studytracker.entity.Course;
import com.nhatlong.studytracker.entity.PracticeLog;
import com.nhatlong.studytracker.entity.StudyTask;
import com.nhatlong.studytracker.repository.CourseRepository;
import com.nhatlong.studytracker.repository.PracticeLogRepository;
import com.nhatlong.studytracker.repository.StudyTaskRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudyTrackerService {
    private final CourseRepository courseRepository;
    private final StudyTaskRepository taskRepository;
    private final PracticeLogRepository practiceLogRepository;

    public StudyTrackerService(
            CourseRepository courseRepository,
            StudyTaskRepository taskRepository,
            PracticeLogRepository practiceLogRepository) {
        this.courseRepository = courseRepository;
        this.taskRepository = taskRepository;
        this.practiceLogRepository = practiceLogRepository;
    }

    public List<Course> getCourses(Integer semester) {
        if (semester != null) {
            return courseRepository.findBySemesterOrderByCodeAsc(semester);
        }
        return courseRepository.findAllByOrderBySemesterAscCodeAsc();
    }

    @Transactional
    public Course createOrUpdateCourse(Course course) {
        course.setCode(course.getCode());
        return courseRepository.save(course);
    }

    public List<StudyTask> getTasks(String courseCode) {
        if (courseCode != null && !courseCode.isBlank()) {
            return taskRepository.findByCourseCodeOrderByIdDesc(courseCode.trim().toUpperCase());
        }
        return taskRepository.findAllByOrderByDoneAscIdDesc();
    }

    @Transactional
    public StudyTask addTask(StudyTask task) {
        String code = requireExistingCourse(task.getCourseCode());
        task.setCourseCode(code);
        return taskRepository.save(task);
    }

    @Transactional
    public StudyTask toggleTask(Long taskId) {
        StudyTask task = taskRepository.findById(taskId)
                .orElseThrow(() -> new IllegalArgumentException("Task not found: " + taskId));
        task.setDone(!task.isDone());
        return taskRepository.save(task);
    }

    public List<PracticeLog> getPracticeLogs(String courseCode) {
        if (courseCode != null && !courseCode.isBlank()) {
            return practiceLogRepository.findByCourseCodeOrderByLoggedDateDescIdDesc(courseCode.trim().toUpperCase());
        }
        return practiceLogRepository.findAllByOrderByLoggedDateDescIdDesc();
    }

    @Transactional
    public PracticeLog logPractice(PracticeLog log) {
        String code = requireExistingCourse(log.getCourseCode());
        log.setCourseCode(code);
        if (log.getLoggedDate() == null) {
            log.setLoggedDate(LocalDate.now());
        }
        return practiceLogRepository.save(log);
    }

    public Map<String, Object> getSummary() {
        List<Course> courses = courseRepository.findAll();
        double weightedSum = 0.0;
        int completedCredits = 0;
        int inProgressCount = 0;

        for (Course c : courses) {
            if (c.getStatus() == Course.Status.COMPLETED && c.getScore() != null) {
                weightedSum += c.getScore() * c.getCredits();
                completedCredits += c.getCredits();
            } else if (c.getStatus() == Course.Status.IN_PROGRESS) {
                inProgressCount++;
            }
        }

        double gpa = completedCredits == 0 ? 0.0 : Math.round((weightedSum / completedCredits) * 100.0) / 100.0;
        int totalPracticeMinutes = practiceLogRepository.findAll().stream()
                .mapToInt(PracticeLog::getMinutes)
                .sum();
        long pendingTasks = taskRepository.findAll().stream()
                .filter(t -> !t.isDone())
                .count();

        Map<String, Object> summary = new LinkedHashMap<>();
        summary.put("completedGpa", gpa);
        summary.put("completedCredits", completedCredits);
        summary.put("inProgressCourses", inProgressCount);
        summary.put("totalCourses", courses.size());
        summary.put("pendingTasks", pendingTasks);
        summary.put("totalPracticeMinutes", totalPracticeMinutes);
        return summary;
    }

    private String requireExistingCourse(String courseCode) {
        String code = courseCode.trim().toUpperCase();
        if (!courseRepository.existsById(code)) {
            throw new IllegalArgumentException("Unknown course code: " + code);
        }
        return code;
    }
}
