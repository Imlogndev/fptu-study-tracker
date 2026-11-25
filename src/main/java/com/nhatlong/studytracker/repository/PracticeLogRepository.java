package com.nhatlong.studytracker.repository;

import com.nhatlong.studytracker.entity.PracticeLog;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PracticeLogRepository extends JpaRepository<PracticeLog, Long> {
    List<PracticeLog> findByCourseCodeOrderByLoggedDateDescIdDesc(String courseCode);
    List<PracticeLog> findAllByOrderByLoggedDateDescIdDesc();
}
