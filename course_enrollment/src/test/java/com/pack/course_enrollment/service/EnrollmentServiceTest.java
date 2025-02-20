package com.pack.course_enrollment.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import com.pack.course_enrollment.dto.CourseDto;
import com.pack.course_enrollment.dto.EnrollmentDto;
import com.pack.course_enrollment.dto.ResponseDto;
import com.pack.course_enrollment.entity.CourseEnrollment;
import com.pack.course_enrollment.entity.CourseStatus;
import com.pack.course_enrollment.entity.Courses;
import com.pack.course_enrollment.entity.Trainee;
import com.pack.course_enrollment.exception.CourseException;
import com.pack.course_enrollment.exception.EnrollmentException;
import com.pack.course_enrollment.exception.TraineeException;
import com.pack.course_enrollment.repository.CourseEnrollmentRepository;
import com.pack.course_enrollment.repository.CoursesRepository;
import com.pack.course_enrollment.repository.TraineeRepository;

@SpringBootTest
class EnrollmentServiceTest {

	@InjectMocks
	private EnrollmentServiceImpl enrollmentService;
	
	@Mock
	private TraineeRepository traineeRepository;
	@Mock
	private CoursesRepository courseRepository;
	@Mock
	private CourseEnrollmentRepository courseEnrollmentRepo;
	
	Trainee trainee =new Trainee(1l,"sasi",LocalDate.of(2024, 02, 01),"sasi@gmail.com","sasi@123");
	Courses course=new Courses(1l,"java",LocalDate.of(2024, 04, 02),LocalDate.of(2024, 04, 05),10);
	CourseEnrollment enrollment=new CourseEnrollment(1l,trainee,course,LocalDate.now(),CourseStatus.ENROLLED);
	
	@Test
	void testEnrollCourses() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(course);
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		ResponseDto responseDto=new ResponseDto("200","courses enrolled successfully");
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseRepository.findAllById(any())).thenReturn(courseList);
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCode(any())).thenReturn(enrollmentList);
		Mockito.when(courseEnrollmentRepo.findByCourseIdAndTraineeSapCode(any(),any())).thenReturn(Optional.empty());
		
		ResponseDto response=enrollmentService.enrollCourses(1l, enrollmentDto);
		assertEquals(responseDto.getStatusCode(),response.getStatusCode());
		assertEquals(responseDto.getStatusMessage(),response.getStatusMessage());
		
	}
	
	@Test
	void testTraineeNotFound() {
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.empty());
		TraineeException exception=assertThrows(TraineeException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC003",exception.getErrorCode());
	}
	
	@Test
	void testDuplicateInputs() {
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		courseIdList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		CourseException exception=assertThrows(CourseException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC004",exception.getErrorCode());
	}
	@Test
	void testIfSomeIdsAreNotPresent() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(course);
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		courseIdList.add(2l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseRepository.findAllById(any())).thenReturn(courseList);
		CourseException exception=assertThrows(CourseException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC005",exception.getErrorCode());

	}
	@Test
	void testIfCourseAlreadyStarted() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(new Courses(1l,"java",LocalDate.of(2024, 03, 22),LocalDate.of(2024, 04, 05),10));
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseRepository.findAllById(any())).thenReturn(courseList);
		CourseException exception=assertThrows(CourseException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC006",exception.getErrorCode());
	}
	@Test
	void testIfCourseAlreadyEnded() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(new Courses(1l,"java",LocalDate.of(2024, 03, 22),LocalDate.of(2024, 03, 25),10));
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseRepository.findAllById(any())).thenReturn(courseList);
		CourseException exception=assertThrows(CourseException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC006",exception.getErrorCode());
	}
	@Test
	void testIfAnotherCourseEnrolledAtThatTime() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(new Courses(1l,"java",LocalDate.of(2024, 04, 03),LocalDate.of(2024, 04, 05),10));
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseRepository.findAllById(any())).thenReturn(courseList);
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCode(any())).thenReturn(enrollmentList);
		CourseException exception=assertThrows(CourseException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC007",exception.getErrorCode());
	}
	@Test
	void testIfCourseAlreadyEnrolled() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(course);
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseRepository.findAllById(any())).thenReturn(courseList);
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCode(any())).thenReturn(enrollmentList);
		Mockito.when(courseEnrollmentRepo.findByCourseIdAndTraineeSapCode(any(),any())).thenReturn(Optional.of(enrollment));
		CourseException exception=assertThrows(CourseException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC008",exception.getErrorCode());
	}
	@Test
	void testInputCousesCannotBeEnrolledBecauseOfOverlapping() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(course);
		courseList.add(new Courses(2l,"java",LocalDate.of(2024, 04, 03),LocalDate.of(2024, 04, 07),10));
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		List<Long> courseIdList=new ArrayList<>();
		courseIdList.add(1l);
		courseIdList.add(2l);
		EnrollmentDto enrollmentDto=new EnrollmentDto(courseIdList);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseRepository.findAllById(any())).thenReturn(courseList);
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCode(any())).thenReturn(Collections.emptyList());
		Mockito.when(courseEnrollmentRepo.findByCourseIdAndTraineeSapCode(any(),any())).thenReturn(Optional.empty());
		EnrollmentException exception=assertThrows(EnrollmentException.class,
				()->enrollmentService.enrollCourses(1l, enrollmentDto));
		assertEquals("TC009",exception.getErrorCode());
	}
	
	//test to view enrollments
	
	@Test
	void testGetEnrolledCoursesIfStatusNull() {
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		Pageable pageable=Mockito.mock(Pageable.class);
		Page<CourseEnrollment> mockPage=new PageImpl<>(enrollmentList,pageable,enrollmentList.size());
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCode(any(), any(Pageable.class))).thenReturn(mockPage);
		Page<CourseDto> response=enrollmentService.getCourses(1l, null, 0, 1);
		assertEquals(enrollmentList.size(),response.getContent().size());
	}
	@Test
	void testTraineeNotWhileGettingEnrollmentsFound() {
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.empty());
		TraineeException exception=assertThrows(TraineeException.class,
				()->enrollmentService.getCourses(1l, null, 0, 1));
		assertEquals("TC003",exception.getErrorCode());
	}
	@Test
	void testIfNoEnrolledCoursesIfStatusNull() {
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		Pageable pageable=Mockito.mock(Pageable.class);
		Page<CourseEnrollment> mockPage=new PageImpl<>(Collections.emptyList(),pageable,0);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCode(any(), any(Pageable.class))).thenReturn(mockPage);
		EnrollmentException exception=assertThrows(EnrollmentException.class,
				()->enrollmentService.getCourses(1l, null, 0, 1));
		assertEquals("TC009",exception.getErrorCode());	
	}
	@Test
	void testGetEnrolledCoursesIfStatusNotNull() {
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		Pageable pageable=Mockito.mock(Pageable.class);
		Page<CourseEnrollment> mockPage=new PageImpl<>(enrollmentList,pageable,enrollmentList.size());
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCodeAndStatus(any(), any(), any(Pageable.class))).thenReturn(mockPage);
		Page<CourseDto> response=enrollmentService.getCourses(1l, CourseStatus.ENROLLED, 0, 1);
		assertEquals(enrollmentList.size(),response.getContent().size());
	}
	@Test
	void testIfNoEnrolledCoursesIfStatusNotNull() {
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		Pageable pageable=Mockito.mock(Pageable.class);
		Page<CourseEnrollment> mockPage=new PageImpl<>(Collections.emptyList(),pageable,0);
		Mockito.when(traineeRepository.findById(anyLong())).thenReturn(Optional.of(trainee));
		Mockito.when(courseEnrollmentRepo.findAllByTraineeSapCodeAndStatus(any(), any(), any(Pageable.class))).thenReturn(mockPage);
		EnrollmentException exception=assertThrows(EnrollmentException.class,
				()->enrollmentService.getCourses(1l, CourseStatus.ENROLLED, 0, 1));
		assertEquals("TC009",exception.getErrorCode());	
	}
	
	@Test
	void testSchedular() {
		List<CourseEnrollment> enrollmentList=new ArrayList<>();
		enrollmentList.add(enrollment);
		enrollmentList.add(new CourseEnrollment(2l,trainee,new Courses(2l,"java",LocalDate.of(2024, 03, 28),LocalDate.of(2024, 03, 30),10),LocalDate.now(),CourseStatus.INPROGRESS));
		enrollmentList.add(new CourseEnrollment(3l,trainee,new Courses(3l,"java",LocalDate.of(2024, 03, 27),LocalDate.of(2024, 03, 28),10),LocalDate.now(),CourseStatus.COMPLETED));

		Mockito.when(courseEnrollmentRepo.findAll()).thenReturn(enrollmentList);
		enrollmentService.setConnectionEstablished();
		verify(courseEnrollmentRepo,times(1)).findAll();
		
	}
}
