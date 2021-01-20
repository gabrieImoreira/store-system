package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.Request;

@Repository
public interface RequestRepository extends JpaRepository<Request, Integer> {
	
}

