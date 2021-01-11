package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
	
}

