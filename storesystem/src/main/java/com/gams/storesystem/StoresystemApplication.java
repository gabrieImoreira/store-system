package com.gams.storesystem;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StoresystemApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(StoresystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	}

}
