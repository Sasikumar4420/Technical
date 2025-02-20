package com.demo.movieticket.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data

public class Reserves {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long reserveId;
	@Enumerated(EnumType.STRING)
	private ReserveStatus reserveStatus;
	@ManyToOne
	private Shows shows;
	@ManyToOne
	private Users users;
	
	
	
	
}
