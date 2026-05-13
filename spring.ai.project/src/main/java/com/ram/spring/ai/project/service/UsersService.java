package com.ram.spring.ai.project.service;

import com.ram.spring.ai.project.dto.UsersDto;
import com.ram.spring.ai.project.models.Users;

public interface UsersService {

	Users saveUserDetails(UsersDto dto);

}
