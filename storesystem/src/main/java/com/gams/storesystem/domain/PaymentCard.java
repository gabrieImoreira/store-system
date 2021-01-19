package com.gams.storesystem.domain;

import com.gams.storesystem.domain.enums.PaymentState;

public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer plotsNumber; //parcelas
	
	public PaymentCard() {
		
	}

	public PaymentCard(Integer id, PaymentState state, Order order, Integer plotsNumber) {
		super(id, state, order);
		this.plotsNumber = plotsNumber;
	}

	public Integer getPlotsNumber() {
		return plotsNumber;
	}

	public void setPlotsNumber(Integer plotsNumber) {
		this.plotsNumber = plotsNumber;
	}
	
	
}
