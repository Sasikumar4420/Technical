package com.demo.movieticket.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.demo.movieticket.dto.ReservedSeatRequestDto;
import com.demo.movieticket.dto.ResponseDto;
import com.demo.movieticket.entity.ReserveStatus;
import com.demo.movieticket.entity.ReservedSeats;
import com.demo.movieticket.entity.Reserves;
import com.demo.movieticket.entity.Seats;
import com.demo.movieticket.entity.Shows;
import com.demo.movieticket.entity.Users;
import com.demo.movieticket.exception.AlreadyReservedException;
import com.demo.movieticket.exception.SeatNotFoundException;
import com.demo.movieticket.exception.ShowNotFoundException;
import com.demo.movieticket.exception.TicketUnderReservationException;
import com.demo.movieticket.exception.UserNotFoundException;
import com.demo.movieticket.repository.ReservedSeatsRepository;
import com.demo.movieticket.repository.ReservesRepository;
import com.demo.movieticket.repository.SeatsRepository;
import com.demo.movieticket.repository.ShowsReposiotry;
import com.demo.movieticket.repository.UserRepository;
import com.demo.movieticket.utils.ResponseMessage;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class ReserveServiceImpl implements ReserveService {
	@Autowired
	UserRepository userRepository;
	@Autowired
	ShowsReposiotry showsReposiotry;
	@Autowired
	SeatsRepository seatsRepository;
	@Autowired
	ReservesRepository reservesRepository;
	@Autowired
	ReservedSeatsRepository reservedSeatsRepository;
	@Autowired
	ResponseMessage responseMessage;

	@Override
	public ResponseDto reserved(Long userId, Long showId, List<ReservedSeatRequestDto> reservedSeatRequestDto) {
		log.info("Inside ReservedServiceImpl - ReservedTickets");
		Users users = userRepository.findById(userId).orElse(null);
		if (users == null) {
			throw new UserNotFoundException(responseMessage.getUserNotFound());
		}
		Shows shows = showsReposiotry.findById(showId).orElse(null);
		if (shows == null) {
			throw new ShowNotFoundException(responseMessage.getShowNotFound());
		}
		List<Reserves> reserves = new ArrayList<>();
		List<ReservedSeats> reserveSeats = new ArrayList<>();
		for (ReservedSeatRequestDto requestDto : reservedSeatRequestDto) {
			Optional<Seats> seats = seatsRepository.findBySeatNameAndShowsShowId(requestDto.getSeatName(), showId);
			if (seats.isEmpty()) {
				throw new SeatNotFoundException(responseMessage.getSeatNotFound());
			}
			Optional<Reserves> showuserdetails = reservesRepository
					.findByShowsShowIdAndUsersUserIdAndReserveStatus(showId, userId, ReserveStatus.RESERVED);
			Optional<ReservedSeats> resserveseatdetails = reservedSeatsRepository
					.findBySeatsName(requestDto.getSeatName());
			if (showuserdetails.isPresent() && resserveseatdetails.isPresent()) {
				throw new AlreadyReservedException(responseMessage.getAlreadyReservedFound());
			}
			Optional<ReservedSeats> seat = reservedSeatsRepository.findBySeatsName(requestDto.getSeatName());
			Optional<Reserves> show = reservesRepository.findByShowsShowIdAndReserveStatus(showId,
					ReserveStatus.RESERVED);

			if (seat.isPresent() && show.isPresent()) {
				throw new TicketUnderReservationException(responseMessage.getSticketUnderReservation());
			}
			Reserves reservesdetails = new Reserves();
			reservesdetails.setUsers(users);
			reservesdetails.setShows(shows);
			reservesdetails.setReserveStatus(ReserveStatus.RESERVED);
			reserves.add(reservesdetails);
			reservesRepository.save(reservesdetails);
			ReservedSeats reservedSeats = new ReservedSeats();
			reservedSeats.setSeatsName(requestDto.getSeatName());
			reservedSeats.setReserves(reservesdetails);
			reserveSeats.add(reservedSeats);
			reservedSeatsRepository.saveAll(reserveSeats);

		}

		ResponseDto response = new ResponseDto();
		response.setCode("1001");
		response.setMessage("Successfully Ticket Reserved...");
		return response;
	}

	@Scheduled(fixedDelay = 900000)        //15 min
	public void updateReservedStatus() {
		List<Reserves> status = reservesRepository.findByReserveStatus(ReserveStatus.RESERVED);
		status.forEach(e -> {
			if (e.getReserveStatus().equals(ReserveStatus.RESERVED)) {
				e.setReserveStatus(ReserveStatus.AVAILABLE);
			} else {
				e.setReserveStatus(ReserveStatus.RESERVED);
			}
		});
		reservesRepository.saveAll(status);
	}

}
