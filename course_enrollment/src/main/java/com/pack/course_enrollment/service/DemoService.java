package com.pack.course_enrollment.service;

import com.pack.course_enrollment.dto.CredentialsDto;
import com.pack.course_enrollment.dto.TokenDto;
import com.pack.course_enrollment.dto.TraineeDto;

public interface DemoService {
	TokenDto saveTrainee(TraineeDto trainee);

	TokenDto checkCredentials(CredentialsDto credentials);
}
