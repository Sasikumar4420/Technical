package com.demo.movieticket.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.movieticket.entity.Seats;

public interface SeatsRepository extends JpaRepository<Seats, Long> {

	Optional<Seats> findBySeatNameAndShowsShowId(String seatName, Long showId);

}
