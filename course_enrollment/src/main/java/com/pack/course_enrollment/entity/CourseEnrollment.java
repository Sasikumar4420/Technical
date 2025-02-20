package com.pack.course_enrollment.entity;

import java.time.LocalDate;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;

@Entity
public class CourseEnrollment {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long enrollmentId;
	@ManyToOne
	@JoinColumn(name="trainee_sapCode")
	private Trainee traineeSapCode;
	@ManyToOne
	@JoinColumn(name="course_id")
	private Courses courseId;
	private LocalDate enrollmentDate;
	@Enumerated(EnumType.STRING)
	private CourseStatus status;
	public Long getEnrollmentId() {
		return enrollmentId;
	}
	public void setEnrollmentId(Long enrollmentId) {
		this.enrollmentId = enrollmentId;
	}
	public Trainee getTraineeSapCode() {
		return traineeSapCode;
	}
	public void setTraineeSapCode(Trainee traineeSapCode) {
		this.traineeSapCode = traineeSapCode;
	}
	public Courses getCourseId() {
		return courseId;
	}
	public void setCourseId(Courses courseId) {
		this.courseId = courseId;
	}
	public LocalDate getEnrollmentDate() {
		return enrollmentDate;
	}
	public void setEnrollmentDate(LocalDate enrollmentDate) {
		this.enrollmentDate = enrollmentDate;
	}
	public CourseStatus getStatus() {
		return status;
	}
	public void setStatus(CourseStatus status) {
		this.status = status;
	}
	public CourseEnrollment(Long enrollmentId, Trainee traineeSapCode, Courses courseId, LocalDate enrollmentDate,
			CourseStatus status) {
		super();
		this.enrollmentId = enrollmentId;
		this.traineeSapCode = traineeSapCode;
		this.courseId = courseId;
		this.enrollmentDate = enrollmentDate;
		this.status = status;
	}
	public CourseEnrollment() {
		super();
	}
	
	

}
