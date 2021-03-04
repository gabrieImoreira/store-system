package com.gams.storesystem.services;

import java.util.Date;
import java.util.Optional;

import com.gams.storesystem.domain.ItemRequest;
import com.gams.storesystem.domain.PaymentBoleto;
import com.gams.storesystem.domain.enums.PaymentState;
import com.gams.storesystem.repositories.ItemRequestRepository;
import com.gams.storesystem.repositories.PaymentRepository;
import com.gams.storesystem.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.gams.storesystem.domain.Request;
import com.gams.storesystem.repositories.RequestRepository;
import com.gams.storesystem.services.exceptions.ObjectNotFoundException;

@Service
public class RequestService {

	@Autowired
	private BoletoService boletoService;

	@Autowired
	private RequestRepository repo;

	@Autowired
	private PaymentRepository paymentRepository;

	@Autowired
	private ProductService productService;

	@Autowired
	private ItemRequestRepository itemRequestRepository;

	public Request search(Integer id) {
		Optional<Request> obj = repo.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException(
				"Objeto não encontrado! Id: " + id + ", Tipo: " + Request.class.getName()));
	}

	public Request insert(Request obj) {
		obj.setId(null);
		obj.setMoment(new Date());
		obj.getPayment().setState(PaymentState.PENDENTE);
		obj.getPayment().setRequest(obj);
		if(obj.getPayment() instanceof PaymentBoleto){
			PaymentBoleto paym = (PaymentBoleto) obj.getPayment();
			boletoService.setPaymentWithBoleto(paym, obj.getMoment());
		}
		obj = repo.save(obj);
		paymentRepository.save(obj.getPayment());
		for(ItemRequest ip : obj.getItems()){
			ip.setDiscount(0.0);
			ip.setPrice(productService.search(ip.getProduct().getId()).getPrice());
			ip.setRequest(obj);
		}
		itemRequestRepository.saveAll(obj.getItems());
		return obj;
	}
}