package com.gams.storesystem.domain;

import javax.persistence.Entity;

import com.gams.storesystem.domain.enums.PaymentState;

@Entity
public class PaymentCard extends Payment {
	private static final long serialVersionUID = 1L;

	private Integer plotsNumber; //parcelas
	
	public PaymentCard() {
		
	}

	public PaymentCard(Integer id, PaymentState state, Request request, Integer plotsNumber) {
		super(id, state, request);
		this.plotsNumber = plotsNumber;
	}

	public Integer getPlotsNumber() {
		return plotsNumber;
	}

	public void setPlotsNumber(Integer plotsNumber) {
		this.plotsNumber = plotsNumber;
	}
	
	
}
