package com.pack.parkingspot.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pack.parkingspot.entity.AvailableSpots;
import com.pack.parkingspot.entity.ParkingSlots;
import com.pack.parkingspot.enums.SlotStatus;

@Repository
public interface AvailableSpotsRepository extends JpaRepository<AvailableSpots,Integer>{

	Optional<AvailableSpots> findByparkingSlot(ParkingSlots parkingSlot);

	List<AvailableSpots> findByStatus(SlotStatus notavailable);

}
