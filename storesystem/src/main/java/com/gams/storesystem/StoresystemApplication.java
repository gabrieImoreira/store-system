package com.gams.storesystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.repositories.CategoryRepository;

@SpringBootApplication
public class StoresystemApplication implements CommandLineRunner {

	@Autowired //data injection
	private CategoryRepository categoryRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(StoresystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2)); //save and inject data in db
		
	}

}
