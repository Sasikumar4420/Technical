package com.demo.movieticket.test.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.demo.movieticket.dto.ReservedSeatRequestDto;
import com.demo.movieticket.dto.ResponseDto;
import com.demo.movieticket.entity.Reserves;
import com.demo.movieticket.entity.Seats;
import com.demo.movieticket.entity.Shows;
import com.demo.movieticket.entity.Users;
import com.demo.movieticket.repository.ReservedSeatsRepository;
import com.demo.movieticket.repository.ReservesRepository;
import com.demo.movieticket.repository.SeatsRepository;
import com.demo.movieticket.repository.ShowsReposiotry;
import com.demo.movieticket.repository.UserRepository;
import com.demo.movieticket.service.ReserveServiceImpl;

@SpringBootTest
public class ReserveServiceTest {
	@Mock
	UserRepository userRepository;
	@Mock
	ShowsReposiotry showsReposiotry;
	@Mock
	SeatsRepository seatsRepository;
	@Mock
	ReservesRepository reservesRepository;
	@Mock
	ReservedSeatsRepository reservedSeatsRepository;
	@InjectMocks
	ReserveServiceImpl reserveServiceImpl;
	
	@Test
	void TestReserved() {
		Users users=new Users(1l, "sasi", "sasi@gmail.com", 987654l);
		LocalDateTime showDateTime1 = LocalDateTime.of(2024, 04, 04, 12, 0);
		Shows shows=new  Shows(1l, "Kanchana", showDateTime1);
		Seats seats=new Seats(1l, "A1", new Shows(1l, "Kanchana", showDateTime1));
		
		List<ReservedSeatRequestDto> reservedSeatRequestDtos=new ArrayList<>();
		ReservedSeatRequestDto reservedSeatRequestDto= new ReservedSeatRequestDto();
		reservedSeatRequestDto.setSeatName("A1");
		reservedSeatRequestDtos.add(reservedSeatRequestDto);
		when(userRepository.findById(1l)).thenReturn(Optional.of(users));
		when(showsReposiotry.findById(1l)).thenReturn(Optional.of(shows));
		when(seatsRepository.findBySeatNameAndShowsShowId("A1", 1l)).thenReturn(null);
		ResponseDto responseDto=new ResponseDto();
		responseDto.setCode("1001");
		responseDto.setMessage("Successfully Ticket Reserved...");
		ResponseDto result=reserveServiceImpl.reserved(1l, 1l, reservedSeatRequestDtos);
		assertEquals("1001",result.getCode());
	}
}
