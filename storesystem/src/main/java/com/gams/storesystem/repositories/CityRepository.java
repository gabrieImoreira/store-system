package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.City;

@Repository
public interface CityRepository extends JpaRepository<City, Integer> {
	
}

