package com.pack.course_enrollment.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

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

@Service
public class EnrollmentServiceImpl implements EnrollmentService {
	
	Logger logger = LoggerFactory.getLogger(getClass());

	private final TraineeRepository traineeRepository;
	private final CoursesRepository courseRepository;
	private final CourseEnrollmentRepository courseEnrollmentRepo;

	public EnrollmentServiceImpl(TraineeRepository traineeRepository, CoursesRepository courseRepository,
			CourseEnrollmentRepository courseEnrollmentRepo) {
		super();
		this.traineeRepository = traineeRepository;
		this.courseRepository = courseRepository;
		this.courseEnrollmentRepo = courseEnrollmentRepo;
	}
	
	
	/**
	 * This method takes list of course id's and employee sap code using those details it will validate
	 * the input if all conditions satisfy then the list of courses will be enrolled
	 * @param sapCode
	 * @param enrollmentDto
	 * @return successfully enrolled message
	 */
	@Override
	public ResponseDto enrollCourses(Long sapCode, EnrollmentDto enrollmentDto) {
		logger.info("inside enroll courses service method");
		Trainee trainee = traineeRepository.findById(sapCode)
				.orElseThrow(() -> new TraineeException("TC003", "trainee not found "));
		if (enrollmentDto.getCourseId().size() != enrollmentDto.getCourseId().stream().distinct().count()) {
			logger.warn("the entered input contains duplicate values");
			throw new CourseException("TC004", "please do not enter same course id again");
		}
		List<Long> courseIdList = enrollmentDto.getCourseId().stream().toList();
		List<Courses> courseList = courseRepository.findAllById(courseIdList);
		if (courseList.size() != courseIdList.size()) {
			logger.warn("the entered input course id's are invalid");
			throw new CourseException("TC005", "please enter valid course id");
		}
		List<CourseEnrollment> enrolledCoursesByTrainee = courseEnrollmentRepo.findAllByTraineeSapCode(trainee);

		Predicate<Courses> validCourses = request -> {
			
			if (request.getStartDate().isBefore(LocalDate.now()) || request.getEndDate().isBefore(LocalDate.now())) {
				logger.error("the entered course is already started or ended");
				throw new CourseException("TC006",
						"course is already started or ended you can't enroll now " + request.getCourseName());
			} else {
				List<CourseEnrollment> courseEnrollment = enrolledCoursesByTrainee.stream()
						.filter(req -> req.getCourseId().getStartDate().isBefore(request.getStartDate())
								&& req.getCourseId().getEndDate().isAfter(request.getStartDate()))
						.toList();
				if (courseEnrollment.size() > 0) {
					logger.error("you have already enrolled course on that time period");
					throw new CourseException("TC007", "you have already enrolled course on that time period");
				} else if (enrolledCoursesByTrainee.stream().anyMatch(req->req.getCourseId().equals(request))) {
					logger.error("this course is already enrolled");
					throw new CourseException("TC008", "this course is already enrolled " + request.getCourseId());
				}
				return true;
			}
		};

		List<Courses> validCoursesToEnroll = courseList.stream().filter(validCourses).toList();
		List<Courses> list = validCoursesToEnroll.stream().filter(req -> {
			if (checkForValidCourses(validCoursesToEnroll, req)) {
				return true;
			}
			return false;
		}).toList();
		if (list.size() != validCoursesToEnroll.size()) {
			logger.error("the all input courses can't be enrolled");
			throw new EnrollmentException("TC009", "the all input courses can't be enrolled");
		}
		List<CourseEnrollment> enrollmentList = list.stream().map(request -> {
			CourseEnrollment enrollment = new CourseEnrollment();
			enrollment.setTraineeSapCode(trainee);
			enrollment.setEnrollmentDate(LocalDate.now());
			enrollment.setStatus(CourseStatus.ENROLLED);
			enrollment.setCourseId(request);
			return enrollment;
		}).toList();
		courseEnrollmentRepo.saveAll(enrollmentList);
		return new ResponseDto("200", "courses enrolled successfully");
	}
	
	/**
	 * This method takes list of courses and each course to check the given input is valid or not
	 * @param validCoursesToEnroll
	 * @param req
	 * @return if that course is valid return true else false
	 */
	private boolean checkForValidCourses(List<Courses> validCoursesToEnroll, Courses req) {
		logger.info("inside  check valid courses method for input data checking");
		int c = validCoursesToEnroll.size();
		for (Courses course : validCoursesToEnroll) {
			if (req.getStartDate().isAfter(course.getStartDate()) && req.getStartDate().isBefore(course.getEndDate())) {
				c--;
			}
		}
		if (c == validCoursesToEnroll.size()) {
			return true;
		}
		return false;
	}
	
	/**
	 * This method used to return list of enrolled courses by the input parameters
	 * @param sapCode
	 * @param pageNumber
	 * @param pageSize
	 * @return list of enrolled courses
	 */
	@Override
	public Page<CourseDto> getCourses(Long sapCode, CourseStatus status, Integer pageNumber, Integer pageSize) {
		logger.info("inside get courses method");
		Trainee trainee = traineeRepository.findById(sapCode)
				.orElseThrow(() -> new TraineeException("TC003", "trainee not found "));
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		if (status == null) {
			Page<CourseEnrollment> enrolledCourses = courseEnrollmentRepo.findAllByTraineeSapCode(trainee, pageable);
			if (enrolledCourses.getContent().size() == 0) {
				logger.error("no enrollments to the trainee if no status");
				throw new EnrollmentException("TC009", "no enrollments for the trainee");
			}
			List<CourseDto> page = enrolledCourses.stream().map(request -> {
				CourseDto dto = new CourseDto();
				BeanUtils.copyProperties(request.getCourseId(), dto);
				dto.setStatus(request.getStatus());
				return dto;
			}).toList();
			return new PageImpl<>(page);

		} else {
			Page<CourseEnrollment> enrolledCourses = courseEnrollmentRepo.findAllByTraineeSapCodeAndStatus(trainee,
					status, pageable);
			if (enrolledCourses.getContent().size() == 0) {
				logger.error("no enrollments to the trainee with given status");
				throw new EnrollmentException("TC009", "no enrollments for the trainee");
			}
			List<CourseDto> page = enrolledCourses.stream().map(request -> {
				CourseDto dto = new CourseDto();
				BeanUtils.copyProperties(request.getCourseId(), dto);
				dto.setStatus(request.getStatus());
				return dto;
			}).toList();
			return new PageImpl<>(page);
		}

	}
	/**
	 * This schedular is used to change the status of the enrolled courses 
	 * based on its start and end date
	 */
	@Scheduled(fixedRate =1000*600)
	public void setConnectionEstablished() {
		logger.info("inside schedular message");
		List<CourseEnrollment> requestList = courseEnrollmentRepo.findAllByStatusNot(CourseStatus.COMPLETED);
		UnaryOperator<CourseEnrollment> setStatus = request -> {
			if (request.getCourseId().getStartDate().isAfter(LocalDate.now())) {
				request.setStatus(CourseStatus.ENROLLED);
			} else if (request.getCourseId().getStartDate().isBefore(LocalDate.now())
					&& LocalDate.now().isBefore(request.getCourseId().getEndDate())) {
				request.setStatus(CourseStatus.INPROGRESS);
			} else if (request.getCourseId().getEndDate().isBefore(LocalDate.now())) {
				request.setStatus(CourseStatus.COMPLETED);
			}
			return request;
		};
		List<CourseEnrollment> updatedList = requestList.stream().map(setStatus::apply).toList();
		courseEnrollmentRepo.saveAll(updatedList);
	}

}
