package com.ass.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.entity.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	@Query("select entity from User entity where email=:email and activated=1")
	public User findByEmail(@Param("email") String email);
	
	@Query("select entity from User entity where activated=0")
	public Page<User> findByActivated(Pageable pageable);
	
	@Query("select entity from User entity where activated=1")
	public Page<User> findByActivated1(Pageable pageable);
	
	
}
