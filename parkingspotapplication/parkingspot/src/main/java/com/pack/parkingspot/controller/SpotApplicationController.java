package com.pack.parkingspot.controller;

import org.apache.juli.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.pack.parkingspot.service.SpotApplicationService;

@RestController
@RequestMapping("/applications")
public class SpotApplicationController {
	
	private final static org.apache.juli.logging.Log logger=LogFactory.getLog(SpotApplicationController.class);

		@Autowired
		SpotApplicationService spotApplicationService;
		/**
		 * 
		 * @param empid
		 * @return application sent message and status as OK
		 */

		@PostMapping("/{empid}")
		public ResponseEntity<Object> spotApplication(@PathVariable(required = false) Long empid){
			logger.info("inside spot application api");
			return new ResponseEntity<>(spotApplicationService.sendApplication(empid),HttpStatus.OK);
		}
}
