package com.gams.storesystem;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gams.storesystem.domain.Category;
import com.gams.storesystem.domain.City;
import com.gams.storesystem.domain.Product;
import com.gams.storesystem.domain.State;
import com.gams.storesystem.repositories.CategoryRepository;
import com.gams.storesystem.repositories.CityRepository;
import com.gams.storesystem.repositories.ProductRepository;
import com.gams.storesystem.repositories.StateRepository;

@SpringBootApplication
public class StoresystemApplication implements CommandLineRunner {

	@Autowired //data injection
	private CategoryRepository categoryRepository;
	@Autowired 
	private ProductRepository productRepository;
	@Autowired 
	private CityRepository cityRepository;
	@Autowired 
	private StateRepository stateRepository;
	
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
		
		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2));
		
		p1.getCategories().addAll(Arrays.asList(cat1));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2));
		p3.getCategories().addAll(Arrays.asList(cat1));
		
		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
	
		categoryRepository.saveAll(Arrays.asList(cat1, cat2)); //save and inject data in db
		productRepository.saveAll(Arrays.asList(p1, p2, p3));
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		
	}

}
