package com.ass.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.entity.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> { 
	@Query("select entity from Order entity where user.id=:user_id")
	public Page<Order> findByUser_id(@Param("user_id") Integer user_id, Pageable pageable);
	
}
