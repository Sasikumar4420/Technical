package com.demo.xyzmc.dto;

import java.time.LocalDate;

import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Past;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class RequestDTO {

	@NotBlank(message = "customer name should not be blank")
	private String customerName;
	@Past(message = "DOB should be past")
	private LocalDate dob;
	@NotBlank(message = "mail id should not be blank")
	@Email(message = "mail Id pattern is invalid")
	private String email;
	@DecimalMin(value = "100000000000", message = "Enter valid Aadhar number")
	@DecimalMax(value = "999999999999", message = "Enter valid Aadhar number")
	private Long aadhaarCard;
	@DecimalMin(value = "1000000000", message = "Enter valid Mobile number")
	@DecimalMax(value = "9999999999", message = "Enter valid Mobile number")
	private Long mobileNumber;

}
