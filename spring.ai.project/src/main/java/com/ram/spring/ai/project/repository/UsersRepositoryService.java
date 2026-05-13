package com.ram.spring.ai.project.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ram.spring.ai.project.models.Users;

@Repository
public interface UsersRepositoryService extends JpaRepository<Users, Long> {

	boolean existsByemailIdIgnoreCaseAndIdNot(String emailId, long id);

	Users findByid(long id);

}
