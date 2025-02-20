package com.demo.movieticket.service;

import java.util.List;

import com.demo.movieticket.dto.ReservedSeatRequestDto;
import com.demo.movieticket.dto.ResponseDto;

public interface ReserveService {

	ResponseDto reserved(Long userId, Long showId, List<ReservedSeatRequestDto> reservedSeatRequestDto);

}
