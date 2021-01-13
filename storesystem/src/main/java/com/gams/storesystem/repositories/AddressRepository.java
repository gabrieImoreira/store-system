package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
	
}

