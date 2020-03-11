package com.cts.training.stockview.userservice.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cts.training.stockview.userservice.entity.UserEntity;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

	public Optional<UserEntity> findByUsername(String username);

	public Optional<UserEntity> findByEmail(String email);

	public Optional<UserEntity> findByUsernameAndPassword(String username, String password);
	
	@Query("SELECT confirmed FROM UserEntity WHERE username=?1")
	public boolean isUserActive(String username);

}
