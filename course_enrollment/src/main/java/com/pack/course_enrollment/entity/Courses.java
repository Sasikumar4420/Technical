package com.pack.course_enrollment.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

@Entity
public class Courses {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long courseId;
	private String courseName;
	private LocalDate startDate;
	private LocalDate endDate;
	private Integer durationPerDay;
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
	public Courses(Long courseId, String courseName, LocalDate startDate, LocalDate endDate, Integer durationPerDay) {
		super();
		this.courseId = courseId;
		this.courseName = courseName;
		this.startDate = startDate;
		this.endDate = endDate;
		this.durationPerDay = durationPerDay;
	}
	public Courses() {
		super();
	}
	@Override
	public String toString() {
		return "Courses [courseId=" + courseId + ", courseName=" + courseName + ", startDate=" + startDate
				+ ", endDate=" + endDate + ", durationPerDay=" + durationPerDay + "]";
	}
	
	
}
