package com.pack.course_enrollment.dto;

import java.time.LocalDate;

import com.pack.course_enrollment.entity.CourseStatus;

public class CourseDto {
	private Long courseId;
	private String courseName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer durationPerDay;
	private CourseStatus status;
	public Long getCourseId() {
		return courseId;
	}
	public void setCourseId(Long courseId) {
		this.courseId = courseId;
	}
	public String getCourseName() {
		return courseName;
	}
	public void setCourseName(String courseName) {
		this.courseName = courseName;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public Integer getDurationPerDay() {
		return durationPerDay;
	}
	public void setDurationPerDay(Integer durationPerDay) {
		this.durationPerDay = durationPerDay;
	}
	public CourseStatus getStatus() {
		return status;
	}
	public void setStatus(CourseStatus status) {
		this.status = status;
	}
	public CourseDto(Long courseId, String courseName, LocalDate startDate, LocalDate endDate, Integer durationPerDay,
			CourseStatus status) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.durationPerDay = durationPerDay;
		this.status = status;
	}
	public CourseDto() {
		super();
	}
	
	
}
