package com.demo.movieticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class ReservedSeats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reservedSeatId;
	private String seatsName;
	@ManyToOne
	private Reserves reserves;

}
