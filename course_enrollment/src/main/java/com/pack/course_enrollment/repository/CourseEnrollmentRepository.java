package com.pack.course_enrollment.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.course_enrollment.entity.CourseEnrollment;
import com.pack.course_enrollment.entity.CourseStatus;
import com.pack.course_enrollment.entity.Courses;
import com.pack.course_enrollment.entity.Trainee;
@Repository
public interface CourseEnrollmentRepository extends JpaRepository<CourseEnrollment,Long>{

	List<CourseEnrollment> findAllByTraineeSapCode(Trainee trainee);


	Optional<CourseEnrollment> findByCourseIdAndTraineeSapCode(Courses request, Trainee trainee);


	Page<CourseEnrollment> findAllByTraineeSapCode(Trainee sapCode, Pageable pageable);


	Page<CourseEnrollment> findAllByTraineeSapCodeAndStatus(Trainee trainee, CourseStatus status, Pageable pageable);


	List<CourseEnrollment> findAllByStatusNot(CourseStatus status);


}
