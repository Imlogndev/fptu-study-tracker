package com.nhatlong.studytracker.repository;

import com.nhatlong.studytracker.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CourseRepository extends JpaRepository<Course, String> {
    List<Course> findBySemesterOrderByCodeAsc(int semester);
    List<Course> findAllByOrderBySemesterAscCodeAsc();
}
