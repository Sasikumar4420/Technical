package com.pack.parkingspot.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.parkingspot.entity.Employee;
import com.pack.parkingspot.entity.ParkingSpotApplications;
@Repository
public interface ParkingSpotApplicationsRepository extends JpaRepository<ParkingSpotApplications,Integer>{

	Optional<ParkingSpotApplications> findByempid(Employee employee);

}
