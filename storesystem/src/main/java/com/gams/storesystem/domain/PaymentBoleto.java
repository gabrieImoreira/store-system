package com.gams.storesystem.domain;

import java.util.Date;

import com.gams.storesystem.domain.enums.PaymentState;

public class PaymentBoleto extends Payment {
	private static final long serialVersionUID = 1L;

	private Date expirationDate;
	private Date paymentDate;
	
	public PaymentBoleto() {
		
	}

	public PaymentBoleto(Integer id, PaymentState state, Order order, Date expirationDate, Date paymentDate) {
		super(id, state, order);
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
