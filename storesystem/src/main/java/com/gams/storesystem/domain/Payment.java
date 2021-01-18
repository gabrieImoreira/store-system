package com.gams.storesystem.domain;

import com.gams.storesystem.domain.enums.PaymentState;

public class Payment {

	private Integer id;
	private PaymentState state;
	
	private Order order;
}
