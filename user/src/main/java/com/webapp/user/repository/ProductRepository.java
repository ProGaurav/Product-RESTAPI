package com.webapp.user.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


import com.webapp.user.entities.Product;
import com.webapp.user.entities.User;


public interface ProductRepository extends JpaRepository<Product,Integer> {

	List<Product> findByUser(User user);
	
	List<Product> findByProductNameContaining(String productName);
	
}
