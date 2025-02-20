package com.pack.course_enrollment.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.course_enrollment.entity.Courses;

@Repository
public interface CoursesRepository extends JpaRepository<Courses,Long>{

}
