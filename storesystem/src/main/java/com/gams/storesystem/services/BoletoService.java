package com.gams.storesystem.services;

import com.gams.storesystem.domain.PaymentBoleto;
import org.springframework.stereotype.Service;

import java.util.Calendar;
import java.util.Date;

@Service
public class BoletoService {

    public void setPaymentWithBoleto(PaymentBoleto paym, Date momentRequest){

        Calendar cal = Calendar.getInstance();
        cal.setTime(momentRequest);
        cal.add(Calendar.DAY_OF_MONTH, 7);
        paym.setPaymentDate(cal.getTime());
    }
}
