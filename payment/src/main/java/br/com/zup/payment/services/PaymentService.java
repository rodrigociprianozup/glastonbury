package br.com.zup.payment.services;

import br.com.zup.payment.event.OrderCreatedEvent;

import java.util.List;

public interface PaymentService {


    public void validPayment(OrderCreatedEvent request);

}
