package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.Payment;

@Repository
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	
}

