package com.gams.storesystem.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.gams.storesystem.domain.Category;

@Repository //with the mark he can communicate with db
public interface CategoryRepository extends JpaRepository<Category, Integer> {

}
