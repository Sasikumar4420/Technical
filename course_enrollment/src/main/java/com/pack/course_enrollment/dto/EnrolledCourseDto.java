package com.pack.course_enrollment.dto;

import org.springframework.data.domain.Page;

public class EnrolledCourseDto {

	private Long sapCode;
	private Page<CourseDto> courses;
	private ResponseDto respone;
	public Long getSapCode() {
		return sapCode;
	}
	public void setSapCode(Long sapCode) {
		this.sapCode = sapCode;
	}
	
	public Page<CourseDto> getCourses() {
		return courses;
	}
	public void setCourses(Page<CourseDto> courses) {
		this.courses = courses;
	}
	public ResponseDto getRespone() {
		return respone;
	}
	public void setRespone(ResponseDto respone) {
		this.respone = respone;
	}
	public EnrolledCourseDto(Long sapCode, Page<CourseDto> courses, ResponseDto respone) {
		super();
		this.sapCode = sapCode;
		this.courses = courses;
		this.respone = respone;
	}
	public EnrolledCourseDto() {
		super();
	}
	
	
	
}
