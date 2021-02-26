package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.Client;
import org.springframework.transaction.annotation.Transactional;

@Repository
public interface ClientRepository extends JpaRepository<Client, Integer> {

    @Transactional(readOnly = true)
    Client findByEmail(String email);
}

