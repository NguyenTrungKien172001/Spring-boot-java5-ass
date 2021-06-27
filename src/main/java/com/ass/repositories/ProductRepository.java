package com.ass.repositories;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.ass.entity.Product;


public interface ProductRepository extends JpaRepository<Product, Integer> {
	@Query("select entity from Product entity where category.id=:category_id and activate=1")
	public List<Product> findBycategory_id(@Param("category_id") Integer category_id);
	
	@Query("select entity from Product entity where activate=1")
	public List<Product> findByProductActivate();
	
	@Query("select entity from Product entity where activate=0")
	public List<Product> findByProductActivate0();
	
	@Query("SELECT p FROM Product p WHERE CONCAT(p.name, ' ', p.price) LIKE %?1% and activate=1")
	public List<Product> search(String keyword);
}
