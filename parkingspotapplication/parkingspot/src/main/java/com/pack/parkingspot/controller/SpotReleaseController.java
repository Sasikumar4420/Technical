package com.pack.parkingspot.controller;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pack.parkingspot.dto.ReleaseDto;
import com.pack.parkingspot.service.SpotReleaseService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/employee")
@Validated
public class SpotReleaseController {
	
	private final static org.apache.juli.logging.Log logger=LogFactory.getLog(SpotReleaseController.class);

	@Autowired
	SpotReleaseService spotReleaseService;
	/**
	 * 
	 * @param releaseDto
	 * @return slot released message and status code as OK
	 */
	@PostMapping
	public ResponseEntity<Object> releaseParkingSlot( @RequestBody @Valid ReleaseDto releaseDto){
		logger.info("inside parking release api");
		return new ResponseEntity<>(spotReleaseService.releaseSpot(releaseDto),HttpStatus.OK);
	}
}
