package com.pack.course_enrollment.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pack.course_enrollment.dto.CourseDto;
import com.pack.course_enrollment.dto.EnrolledCourseDto;
import com.pack.course_enrollment.dto.EnrollmentDto;
import com.pack.course_enrollment.dto.ResponseDto;
import com.pack.course_enrollment.entity.CourseStatus;
import com.pack.course_enrollment.service.EnrollmentService;

@RestController
@RequestMapping("/enrollments")
public class EnrollmentController {
	Logger logger = LoggerFactory.getLogger(getClass());

	private final EnrollmentService enrollmentService;
	@Autowired
	public EnrollmentController(EnrollmentService enrollmentService) {
		this.enrollmentService = enrollmentService;
	}
	/**
	 * This method is used to enroll courses based on sapCode and list of course id's
	 * @param sapCode
	 * @param enrollmentDto
	 * @return response message as successfully enrolled with status code
	 */
	@PostMapping
	public ResponseEntity<ResponseDto> enrollCourses(@RequestHeader Long sapCode ,@RequestBody EnrollmentDto enrollmentDto){
		logger.info("inside enroll courses controller method");
		ResponseDto response=enrollmentService.enrollCourses(sapCode,enrollmentDto);
		return new ResponseEntity<>(response,HttpStatus.CREATED);
	}
	
	/**
	 * This method returns list of courses based on sapCode of trainee and the other status like ENROLLED or COMPLETED or INPROGRESS
	 * @param sapCode
	 * @param status
	 * @param pageNumber
	 * @param pageSize
	 * @return list of enrolled courses based on page number and its size
	 */
	@GetMapping
	public ResponseEntity<EnrolledCourseDto> enrolledCourses(@RequestHeader Long sapCode,
			@RequestParam(required = false) CourseStatus status, @RequestParam(required = false,defaultValue="0") Integer pageNumber,
			@RequestParam(required = false,defaultValue="2") Integer pageSize) { 
		logger.info("inside to get enrolled courses controller method");
		Page<CourseDto> enrolledCourses=enrollmentService.getCourses(sapCode,status, pageNumber, pageSize);
		ResponseDto response=new ResponseDto("200","successfully retrieved data");
		return new ResponseEntity<>(new EnrolledCourseDto(sapCode,enrolledCourses,response),HttpStatus.OK);

	}
}
