package com.example.carrental.dto;

import java.math.BigDecimal;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class VehicleRequestDto {
	@NotBlank(message = "Brand name cannot be blank")
	private String brand;
	@DecimalMin(value = "1", message = "please enter the correct price")
	private BigDecimal pricePerDay;
	@NotBlank(message = "Registration number cannot be empty")
	@Size(min=5,max=5,message = "Invalid Registration number")
	private String regNo;
	@NotBlank(message = "Model name cannot be empty")
	private String model;

}
