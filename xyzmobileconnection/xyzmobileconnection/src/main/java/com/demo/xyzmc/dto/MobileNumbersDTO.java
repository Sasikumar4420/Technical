package com.demo.xyzmc.dto;

import org.springframework.data.domain.Page;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class MobileNumbersDTO {
	private ApiResponse apiResponse;
	private Page<Long> mobileNumbers;
}
