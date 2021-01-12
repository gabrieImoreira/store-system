package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.State;

@Repository
public interface StateRepository extends JpaRepository<State, Integer> {
	
}

