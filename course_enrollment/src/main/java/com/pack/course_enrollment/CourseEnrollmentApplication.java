package com.pack.course_enrollment;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

import com.pack.course_enrollment.entity.Courses;
import com.pack.course_enrollment.repository.CoursesRepository;

@EnableScheduling
@SpringBootApplication
public class CourseEnrollmentApplication implements CommandLineRunner {
	
	private final CoursesRepository courseRepository;
	
	@Autowired
	public CourseEnrollmentApplication(CoursesRepository courseRepository) {
		this.courseRepository = courseRepository;
	}
	public static void main(String[] args) {
		SpringApplication.run(CourseEnrollmentApplication.class, args);
	}
	
	@Override
	public void run(String... args) throws Exception {
//		saveCourses();
		
	}
	
	private void saveCourses() {
		List<Courses> courseList=new ArrayList<>();
		courseList.add(new Courses(1l,"java",LocalDate.of(2024, 04, 01),LocalDate.of(2024, 04, 03),10));
		courseList.add(new Courses(2l,"spring",LocalDate.of(2024, 03, 31),LocalDate.of(2024, 04, 01),10));
		courseList.add(new Courses(3l,"python",LocalDate.of(2024, 04, 03),LocalDate.of(2024, 04, 07),10));
		courseList.add(new Courses(4l,"python",LocalDate.of(2024, 03, 28),LocalDate.of(2024, 04, 07),10));
		courseList.add(new Courses(5l,"python",LocalDate.of(2024, 04, 04),LocalDate.of(2024, 04, 06),10));
		courseRepository.saveAll(courseList);
	}

	

}
