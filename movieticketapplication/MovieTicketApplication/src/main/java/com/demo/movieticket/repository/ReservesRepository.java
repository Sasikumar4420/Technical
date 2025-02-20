package com.demo.movieticket.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.demo.movieticket.entity.ReserveStatus;
import com.demo.movieticket.entity.Reserves;

public interface ReservesRepository extends JpaRepository<Reserves, Long> {

	Optional<Reserves> findByShowsShowIdAndUsersUserIdAndReserveStatus(Long showId, Long userId,
			ReserveStatus reserved);


	Optional<Reserves> findByShowsShowIdAndReserveStatus(Long showId, ReserveStatus reserved);


	List<Reserves> findByReserveStatus(ReserveStatus available);





	

}
