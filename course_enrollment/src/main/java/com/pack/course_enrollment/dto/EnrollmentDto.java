package com.pack.course_enrollment.dto;

import java.util.List;

public class EnrollmentDto {
	private List<Long> courseId;

	public List<Long> getCourseId() {
		return courseId;
	}

	public void setCourseId(List<Long> courseId) {
		this.courseId = courseId;
	}

	public EnrollmentDto(List<Long> courseId) {
		super();
		this.courseId = courseId;
	}

	public EnrollmentDto() {
		super();
	}
	
	
		
}
