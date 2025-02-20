package com.demo.movieticket.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.movieticket.entity.Bookings;

public interface BookingsRepository extends JpaRepository<Bookings, Long>{

}
