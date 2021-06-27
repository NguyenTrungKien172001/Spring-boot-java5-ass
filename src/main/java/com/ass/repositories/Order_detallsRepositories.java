package com.ass.repositories;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.entity.Order_detalls;

public interface Order_detallsRepositories extends JpaRepository<Order_detalls, Integer> {
	@Query("select entity from Order_detalls entity where order.id=:order_id")
	public Page<Order_detalls> findByOrder_id(@Param("order_id") Integer user_id, Pageable pageable);
	
}
