package com.pack.course_enrollment.service;

import org.springframework.data.domain.Page;

import com.pack.course_enrollment.dto.CourseDto;
import com.pack.course_enrollment.dto.EnrollmentDto;
import com.pack.course_enrollment.dto.ResponseDto;
import com.pack.course_enrollment.entity.CourseStatus;

public interface EnrollmentService {
	ResponseDto enrollCourses(Long sapCode, EnrollmentDto enrollmentDto);

	Page<CourseDto> getCourses(Long sapCode, CourseStatus status,Integer pageNumber,Integer pageSize);

}
