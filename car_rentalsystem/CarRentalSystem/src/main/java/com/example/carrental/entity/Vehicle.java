package com.example.carrental.entity;

import java.math.BigDecimal;

import com.example.carrental.dto.Status;
import com.example.carrental.dto.Type;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Vehicle {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long vehicleId;		
	private String brand;
	@Enumerated(EnumType.STRING)
	private Type type;		
	@Enumerated(EnumType.STRING)
	private Status status;									
	private BigDecimal pricePerDay;					
	private String regNo;							
	private String model;					


}
