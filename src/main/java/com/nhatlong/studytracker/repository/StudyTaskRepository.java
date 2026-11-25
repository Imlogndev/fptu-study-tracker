package com.nhatlong.studytracker.repository;

import com.nhatlong.studytracker.entity.StudyTask;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyTaskRepository extends JpaRepository<StudyTask, Long> {
    List<StudyTask> findByCourseCodeOrderByIdDesc(String courseCode);
    List<StudyTask> findAllByOrderByDoneAscIdDesc();
}
