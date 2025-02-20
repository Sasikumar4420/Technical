package com.example.carrental.entity;

import java.math.BigDecimal;
import java.time.LocalDate;

import com.example.carrental.dto.RentalStatus;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rental {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long rentalId;	
	@ManyToOne
	private Vehicle vehicleId;	
	@ManyToOne
	private User userId;					
	private LocalDate startDate;							
	private LocalDate endDate;							
	private BigDecimal totalCost;
	@Enumerated(EnumType.STRING)
	private RentalStatus rentalStatus;							
	private String reason;						


}
