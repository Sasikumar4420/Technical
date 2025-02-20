package com.pack.parkingspot.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.parkingspot.entity.ParkingSlots;
@Repository
public interface ParkingSlotsRepository extends JpaRepository<ParkingSlots,Integer>{

}
