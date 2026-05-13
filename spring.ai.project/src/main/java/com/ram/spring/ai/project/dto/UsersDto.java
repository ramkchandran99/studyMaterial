package com.ram.spring.ai.project.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsersDto {

	private long id;

	@NotBlank (message = "Provide a valid first name.")
	private String firstName;

	@NotBlank (message = "Provide a valid last name.")
	private String lastName;

	@NotBlank (message = "Provide a valid email id.")
	private String emailId;

	@Max(value = 10, message = "Provide a valid 10 digit mobile no.")
	private long mobileNo;

	@NotBlank (message = "Provide a valid password.")
	private String password;

}