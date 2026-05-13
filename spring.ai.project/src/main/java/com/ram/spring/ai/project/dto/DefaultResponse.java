package com.ram.spring.ai.project.dto;

import java.util.Date;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DefaultResponse {

	private Date timestamp;
	private String message;
	private HttpStatus status;

	public DefaultResponse() {
		super();
	}

	public DefaultResponse(Date timestamp, String message, HttpStatus status) {
		super();
		this.timestamp = timestamp;
		this.message = message;
		this.status = status;
	}
}