package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.ItemRequest;

@Repository
public interface ItemRequestRepository extends JpaRepository<ItemRequest, Integer> {
	
}

