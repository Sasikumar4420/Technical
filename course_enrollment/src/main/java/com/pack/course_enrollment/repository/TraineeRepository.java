package com.pack.course_enrollment.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.course_enrollment.entity.Trainee;
@Repository
public interface TraineeRepository extends JpaRepository<Trainee,Long>{
	Optional<Trainee> findByEmail(String email);
}
