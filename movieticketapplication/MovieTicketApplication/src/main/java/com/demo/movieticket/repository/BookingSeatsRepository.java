package com.demo.movieticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.movieticket.entity.BookingSeats;

public interface BookingSeatsRepository extends JpaRepository<BookingSeats, Long>{

}
