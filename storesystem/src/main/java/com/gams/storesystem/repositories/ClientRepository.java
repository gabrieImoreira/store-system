package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.Client;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {
	
}

