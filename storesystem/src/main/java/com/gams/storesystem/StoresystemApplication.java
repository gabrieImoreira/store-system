package com.gams.storesystem;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.gams.storesystem.domain.Address;
import com.gams.storesystem.domain.Category;
import com.gams.storesystem.domain.City;
import com.gams.storesystem.domain.Client;
import com.gams.storesystem.domain.ItemRequest;
import com.gams.storesystem.domain.Payment;
import com.gams.storesystem.domain.PaymentBoleto;
import com.gams.storesystem.domain.PaymentCard;
import com.gams.storesystem.domain.Product;
import com.gams.storesystem.domain.Request;
import com.gams.storesystem.domain.State;
import com.gams.storesystem.domain.enums.PaymentState;
import com.gams.storesystem.domain.enums.TypeClient;
import com.gams.storesystem.repositories.AddressRepository;
import com.gams.storesystem.repositories.CategoryRepository;
import com.gams.storesystem.repositories.CityRepository;
import com.gams.storesystem.repositories.ClientRepository;
import com.gams.storesystem.repositories.ItemRequestRepository;
import com.gams.storesystem.repositories.PaymentRepository;
import com.gams.storesystem.repositories.ProductRepository;
import com.gams.storesystem.repositories.RequestRepository;
import com.gams.storesystem.repositories.StateRepository;

@SpringBootApplication
public class StoresystemApplication implements CommandLineRunner {

	@Autowired //data injection, dependency
	private CategoryRepository categoryRepository;
	@Autowired 
	private ProductRepository productRepository;
	@Autowired 
	private CityRepository cityRepository;
	@Autowired 
	private StateRepository stateRepository;
	@Autowired 
	private ClientRepository clientRepository;
	@Autowired 
	private AddressRepository addressRepository;
	@Autowired 
	private RequestRepository requestRepository;
	@Autowired 
	private PaymentRepository paymentRepository;
	@Autowired 
	private ItemRequestRepository itemRequestRepository;

	
	public static void main(String[] args) {
		SpringApplication.run(StoresystemApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
	
		Category cat1 = new Category(null, "Computing");
		Category cat2 = new Category(null, "Office");
		Category cat3 = new Category(null, "Bed, able and bath");
		Category cat4 = new Category(null, "Eletronics");
		Category cat5 = new Category(null, "Gardening");
		Category cat6 = new Category(null, "Decoration");
		Category cat7 = new Category(null, "Games");
		
		Product p1 = new Product(null, "Computer", 2000.0);
		Product p2 = new Product(null, "Printer", 800.0);
		Product p3 = new Product(null, "Mouse", 80.0);
		Product p4 = new Product(null, "Office table", 300.0);
		Product p5 = new Product(null, "Towel", 50.0);
		Product p6 = new Product(null, "Blanket", 50.0);
		Product p7 = new Product(null, "TV", 1200.0);
		Product p8 = new Product(null, "Light fixture", 800.0);
		Product p9 = new Product(null, "Lamp", 100.0 );
		Product p10 = new Product(null, "Cabinet", 180.0);
		Product p11 = new Product(null, "Shampoo", 90.0);

		cat1.getProducts().addAll(Arrays.asList(p1, p2, p3));
		cat2.getProducts().addAll(Arrays.asList(p2, p4));
		cat3.getProducts().addAll(Arrays.asList(p5, p6));
		cat4.getProducts().addAll(Arrays.asList(p1, p2, p3, p7));
		cat5.getProducts().addAll(Arrays.asList(p8));
		cat6.getProducts().addAll(Arrays.asList(p9, p10));
		cat7.getProducts().addAll(Arrays.asList(p11));

		p1.getCategories().addAll(Arrays.asList(cat1, cat4));
		p2.getCategories().addAll(Arrays.asList(cat1, cat2, cat4));
		p3.getCategories().addAll(Arrays.asList(cat1, cat4));
		p4.getCategories().addAll(Arrays.asList(cat2));
		p5.getCategories().addAll(Arrays.asList(cat3));
		p6.getCategories().addAll(Arrays.asList(cat3));
		p7.getCategories().addAll(Arrays.asList(cat4));
		p8.getCategories().addAll(Arrays.asList(cat5));
		p9.getCategories().addAll(Arrays.asList(cat6));
		p10.getCategories().addAll(Arrays.asList(cat6));
		p11.getCategories().addAll(Arrays.asList(cat7));

		State st1 = new State(null, "Minas Gerais");
		State st2 = new State(null, "São Paulo");
		
		City c1 = new City(null, "Uberlândia", st1);
		City c2 = new City(null, "São Paulo", st2);
		City c3 = new City(null, "Campinas", st2);
		
		Client cli1 = new Client(null, "Maria", "ma.mrp@totUs.com.br", "3321324324", TypeClient.PESSOAFISICA);
		
		Address a1 = new Address(null, "Rua Jose Manoel", "300", "Casa 1", "Vl Matilde", "03511000", cli1, c1);
		Address a2 = new Address(null, "Rua das Palmeiras", "650", "Ap 23", "Gopouva", "07022000", cli1, c2);
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		
		Request req1 = new Request(null, sdf.parse("12/03/2021 00:10"), cli1, a1);
		Request req2 = new Request(null, sdf.parse("18/01/2021 10:14"), cli1, a2);
		
		Payment pay1 = new PaymentCard(null, PaymentState.QUITADO, req1, 6);
		req1.setPayment(pay1);
		
		Payment pay2 = new PaymentBoleto(null, PaymentState.PENDENTE, req2, sdf.parse("20/10/2021 00:00"), null);
		req2.setPayment(pay2);
		
		ItemRequest ir1 = new ItemRequest(req1, p1, 0.00, 1, 2000.00);
		ItemRequest ir2 = new ItemRequest(req1, p3, 0.00, 2, 80.00);
		ItemRequest ir3 = new ItemRequest(req2, p2, 100.00, 1, 800.00);
		
		req1.getItems().addAll(Arrays.asList(ir1, ir2));
		req2.getItems().addAll(Arrays.asList(ir3));
		
		p1.getItems().addAll(Arrays.asList(ir1));
		p1.getItems().addAll(Arrays.asList(ir3));
		p1.getItems().addAll(Arrays.asList(ir2));
		
		
		cli1.getPhones().addAll(Arrays.asList("24098274", "950528184"));
		cli1.getAdresses().addAll(Arrays.asList(a1, a2));

		st1.getCities().addAll(Arrays.asList(c1));
		st2.getCities().addAll(Arrays.asList(c2, c3));
		
		cli1.getRequests().addAll(Arrays.asList(req1, req2));
		
		categoryRepository.saveAll(Arrays.asList(cat1, cat2, cat3, cat4, cat5, cat6, cat7)); //save and inject data in db
		productRepository.saveAll(Arrays.asList(p1, p2, p3, p4, p5, p6, p7, p8, p9, p10, p11));
		stateRepository.saveAll(Arrays.asList(st1, st2));
		cityRepository.saveAll(Arrays.asList(c1, c2, c3));
		clientRepository.saveAll(Arrays.asList(cli1));
		addressRepository.saveAll(Arrays.asList(a1, a2));
		requestRepository.saveAll(Arrays.asList(req1, req2));
		paymentRepository.saveAll(Arrays.asList(pay1, pay2));
		itemRequestRepository.saveAll(Arrays.asList(ir1, ir2, ir3));
	}

}
