package com.productmanager.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.productmanager.model.jpa.Product;

public interface ProductRepository extends JpaRepository<Product,Long> {
	
	Optional<Product> findById(Long id);
	
	List<Product> findAll();
	
	Product save(Product product);

}
