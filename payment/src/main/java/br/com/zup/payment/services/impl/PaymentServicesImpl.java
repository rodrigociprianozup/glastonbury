package br.com.zup.payment.services.impl;

import br.com.zup.payment.event.OrderCreatedEvent;

import br.com.zup.payment.services.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;
import java.util.Random;


@Service
public class PaymentServicesImpl implements PaymentService {

    private KafkaTemplate<String, OrderCreatedEvent> template;


    @Autowired
    public PaymentServicesImpl(KafkaTemplate<String, OrderCreatedEvent> template) {
        this.template = template;
    }

    @Override
    public void validPayment(OrderCreatedEvent request) {

         Random payment = new Random();
         if (payment.nextInt() == 1){
             this.template.send("reject-payment", request);
         } else {
             this.template.send("approved-payment", request);
         }
    }
}
