package br.com.zup.order.service;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.event.OrderCreatedEvent;

import java.util.List;

public interface OrderService {

    String save(CreateOrderRequest request);

    List<OrderResponse> findAll();

    public void orderInventoryReject(OrderCreatedEvent orderCreatedEvent);

    public void paymentReject(OrderCreatedEvent orderCreatedEvent);

    public void paymentApproved(OrderCreatedEvent orderCreatedEvent);
}
