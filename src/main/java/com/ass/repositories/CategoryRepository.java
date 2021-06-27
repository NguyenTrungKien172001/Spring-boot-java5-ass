package com.ass.repositories;


import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.ass.entity.Category;


public interface CategoryRepository extends JpaRepository<Category, Integer> {
	@Query("select entity from Category entity where activate=0")
	public Page<Category> findByActivated(Pageable pageable);
	
	@Query("select entity from Category entity where activate=1")
	public Page<Category> findByActivated1(Pageable pageable);
	
	@Query("select entity from Category entity where activate=1")
	public List<Category> findByCategiryActivate1();
	
	@Query("select entity from Category entity where activate=0")
	public List<Category> findByCategiryActivate0();
}
