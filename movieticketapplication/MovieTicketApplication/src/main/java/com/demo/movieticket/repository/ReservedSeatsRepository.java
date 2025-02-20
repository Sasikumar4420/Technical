package com.demo.movieticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.movieticket.entity.ReservedSeats;

public interface ReservedSeatsRepository extends JpaRepository<ReservedSeats, Long>{

	Optional<ReservedSeats> findBySeatsNameAndReservesReserveId(String seatName, Long reserveId);

	Optional<ReservedSeats> findBySeatsName(String seatName);

}
