package com.example.carrental.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data

@AllArgsConstructor
@NoArgsConstructor
public class VehicleResponseDto {
	private String message;
	private Long statusCode;

}
