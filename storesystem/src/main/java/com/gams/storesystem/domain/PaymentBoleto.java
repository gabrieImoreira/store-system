package com.gams.storesystem.domain;

import java.util.Date;

import javax.persistence.Entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonTypeName;
import com.gams.storesystem.domain.enums.PaymentState;

@Entity
@JsonTypeName("pagamentoComBoleto")
public class PaymentBoleto extends Payment {
	private static final long serialVersionUID = 1L;

	@JsonFormat(pattern="dd/MM/yyyy")
	private Date expirationDate;
	private Date paymentDate;
	
	public PaymentBoleto() {
		
	}

	public PaymentBoleto(Integer id, PaymentState state, Request request, Date expirationDate, Date paymentDate) {
		super(id, state, request);
		this.expirationDate = expirationDate;
		this.paymentDate = paymentDate;
	}

	public Date getExpirationDate() {
		return expirationDate;
	}

	public void setExpirationDate(Date expirationDate) {
		this.expirationDate = expirationDate;
	}

	public Date getPaymentDate() {
		return paymentDate;
	}

	public void setPaymentDate(Date paymentDate) {
		this.paymentDate = paymentDate;
	}
	
	
	
	
}
