package com.nhatlong.studytracker.service;

import com.nhatlong.studytracker.model.Course;
import com.nhatlong.studytracker.model.PracticeLog;
import com.nhatlong.studytracker.model.StudyTask;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CourseServiceTest {

    @Test
    void calculateCompletedGpa_ignoresInProgressCourses() {
        CourseService service = new CourseService();
        service.addCourse(new Course("PRO192", "OOP", 2, 3, Course.Status.COMPLETED, 8.0));
        service.addCourse(new Course("DBI202", "Databases", 3, 3, Course.Status.COMPLETED, 9.0));
        service.addCourse(new Course("CSD201", "DSA", 4, 3, Course.Status.IN_PROGRESS, null));

        assertEquals(8.5, service.calculateCompletedGpa(), 0.001);
    }

    @Test
    void addTask_andMarkDone_updatesTaskStatus() {
        CourseService service = new CourseService();
        service.addCourse(new Course("PRJ301", "Java Web", 4, 3, Course.Status.IN_PROGRESS, null));

        StudyTask task = service.addTask("prj301", "Setup Servlet", StudyTask.Priority.HIGH);
        assertFalse(task.isDone());

        assertTrue(service.markTaskDone(task.getId()));
        assertTrue(task.isDone());
    }

    @Test
    void logPractice_accumulatesMinutesPerCourse() {
        CourseService service = new CourseService();
        service.addCourse(new Course("CSD201", "DSA", 4, 3, Course.Status.IN_PROGRESS, null));

        PracticeLog log1 = service.logPractice("CSD201", "LinkedList", 40);
        PracticeLog log2 = service.logPractice("CSD201", "Stack & Queue", 35);

        assertEquals(2, service.getPracticeLogsByCourse("CSD201").size());
        assertEquals(75, service.getTotalPracticeMinutes("CSD201"));
        assertEquals("CSD201", log1.getCourseCode());
        assertEquals(35, log2.getMinutes());
    }

    @Test
    void logPractice_throwsOnInvalidMinutesOrUnknownCourse() {
        CourseService service = new CourseService();
        service.addCourse(new Course("CSD201", "DSA", 4, 3, Course.Status.IN_PROGRESS, null));

        assertThrows(IllegalArgumentException.class, () -> service.logPractice("CSD201", "Tree", 0));
        assertThrows(IllegalArgumentException.class, () -> service.logPractice("UNKNOWN", "Tree", 30));
    }
}
