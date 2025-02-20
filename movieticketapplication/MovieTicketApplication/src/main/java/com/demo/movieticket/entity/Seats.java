package com.demo.movieticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Data;

@Entity
@Data
public class Seats {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long seatId;
	private String seatName;
	@ManyToOne
	private Shows shows;

	public Seats(Long seatId, String seatName, Shows shows) {
		super();
		this.seatId = seatId;
		this.seatName = seatName;
		this.shows = shows;
	}

	public Seats() {
		super();
		// TODO Auto-generated constructor stub
	}

}
