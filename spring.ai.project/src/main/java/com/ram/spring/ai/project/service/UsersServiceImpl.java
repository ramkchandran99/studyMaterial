package com.ram.spring.ai.project.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.ram.spring.ai.project.dao.UsersRepositoryImpl;
import com.ram.spring.ai.project.dto.UsersDto;
import com.ram.spring.ai.project.exception.CustomRunTimeException;
import com.ram.spring.ai.project.models.Users;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
@RequiredArgsConstructor
public class UsersServiceImpl implements UsersService {

	private final UsersRepositoryImpl usersRepositoryImpl;
	private final PasswordEncoder passwordEncoder;

	public Users saveUserDetails(UsersDto dto) {
		log.info("save user details - service");
		try {
			Users user;

			if (dto.getId() > 0) {
				user = usersRepositoryImpl.findByid(dto.getId());
			} else {
				user = new Users();
			}

			boolean emailExists = usersRepositoryImpl.existsByemailIdIgnoreCaseAndIdNot(dto.getEmailId(), dto.getId());

			if (emailExists) {
				throw new CustomRunTimeException("Email id already exists.");
			}

			user.setEmailId(dto.getEmailId());
			user.setFirstName(dto.getFirstName());
			user.setId(dto.getId());
			user.setLastName(dto.getLastName());
			user.setMobileNo(dto.getMobileNo());
			user.setPassword(passwordEncoder.encode(dto.getPassword()));

			return usersRepositoryImpl.save(user);
		} catch (CustomRunTimeException e) {
			log.error(e.getMessage());
			throw e;
		} catch (Exception e) {
			log.error(e.getMessage());
			throw new CustomRunTimeException("Failed to save user details.");
		}
	}
}