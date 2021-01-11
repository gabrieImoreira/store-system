package com.gams.storesystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.domain.Product;
import com.gams.storesystem.repositories.CategoryRepository;
import com.gams.storesystem.repositories.ProductRepository;

@SpringBootApplication
public class StoresystemApplication implements CommandLineRunner {

	@Autowired //data injection
	private CategoryRepository categoryRepository;
	@Autowired 
	private ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(StoresystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		
		Product p1 = new Product(null, "Computer", 2000.0);
		Product p2 = new Product(null, "Printer", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2)); //save and inject data in db
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
	}

}
