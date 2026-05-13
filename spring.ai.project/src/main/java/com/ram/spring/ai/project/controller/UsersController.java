package com.ram.spring.ai.project.controller;

import java.util.Date;

import org.springframework.http.HttpStatus;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ram.spring.ai.project.dto.DefaultResponse;
import com.ram.spring.ai.project.dto.UsersDto;
import com.ram.spring.ai.project.models.Users;
import com.ram.spring.ai.project.service.UsersService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/service/users")
@RequiredArgsConstructor
@Tag(name = "Users Controller", description = "Users APIs")
@Slf4j
public class UsersController {

	private final UsersService usersService;

	@PostMapping("/save")
	@Operation(summary = "save users Details")
	public DefaultResponse saveUserDetails(@Valid @RequestBody UsersDto dto, BindingResult result) {
		log.info("save users Details - controller");
		if (result.hasErrors()) {
			String firstErrorMessage = result.getFieldError().getDefaultMessage();
			return new DefaultResponse(new Date(), firstErrorMessage, HttpStatus.BAD_REQUEST);
		}
		Users users = usersService.saveUserDetails(dto);
		if (users != null) {
			return new DefaultResponse(new Date(), "User details saved successfully.", HttpStatus.OK);
		} else {
			return new DefaultResponse(new Date(), "Failed to save user details.", HttpStatus.BAD_REQUEST);
		}
	}
}