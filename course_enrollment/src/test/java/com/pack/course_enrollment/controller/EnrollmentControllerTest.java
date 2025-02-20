package com.pack.course_enrollment.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyLong;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.PageImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.pack.course_enrollment.dto.CourseDto;
import com.pack.course_enrollment.dto.EnrolledCourseDto;
import com.pack.course_enrollment.dto.EnrollmentDto;
import com.pack.course_enrollment.dto.ResponseDto;
import com.pack.course_enrollment.entity.CourseStatus;
import com.pack.course_enrollment.service.EnrollmentService;

@SpringBootTest
class EnrollmentControllerTest {
	
	@InjectMocks
	private EnrollmentController enrollmentController;
	@Mock
	private EnrollmentService enrollmentService;
		
	
	@Test
	void testEnrollCourses() {
		List<Long> courseList=new ArrayList<>();
		courseList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseList);
		ResponseDto responseDto=new ResponseDto("200","courses enrolled successfully");
		Mockito.when(enrollmentService.enrollCourses(anyLong(), any())).thenReturn(responseDto);
		ResponseEntity<ResponseDto> response=enrollmentController.enrollCourses(1l, enrollmentDto);
		assertEquals(responseDto.getStatusCode(),response.getBody().getStatusCode());
		assertEquals(responseDto.getStatusMessage(),response.getBody().getStatusMessage());
	}
	@Test
	void testEnrolledCourses() {
		ResponseDto responseDto=new ResponseDto("200","courses retrieved successfully");
		List<CourseDto> courseList=new ArrayList<>();
		courseList.add(new CourseDto(1l,"java",LocalDate.of(2024, 04, 02),LocalDate.of(2024, 04, 06),9,CourseStatus.ENROLLED));
		EnrolledCourseDto enrolledCourse=new EnrolledCourseDto(1l,new PageImpl<>(courseList),responseDto);
		Mockito.when(enrollmentService.getCourses(anyLong(), any(), anyInt(), anyInt())).thenReturn(new PageImpl<>(courseList));
		ResponseEntity<EnrolledCourseDto> response=enrollmentController.enrolledCourses(1l, null, 1, 1);
		assertEquals(HttpStatus.OK,response.getStatusCode());
		assertEquals(enrolledCourse.getSapCode(),response.getBody().getSapCode());
	}
}
