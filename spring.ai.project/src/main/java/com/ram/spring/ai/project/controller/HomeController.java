package com.ram.spring.ai.project.controller;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/service/home")
@CrossOrigin(origins = "*")
@RequiredArgsConstructor
@Slf4j
public class HomeController {
	
	@GetMapping("api/health/check")
	@Operation(summary = "check api health", description = "check api health")
	public void check() {
		log.info("Api is running successfully.");
	}
}