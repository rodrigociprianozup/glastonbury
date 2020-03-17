package br.com.zup.order.service.impl;

import br.com.zup.order.controller.request.CreateOrderRequest;
import br.com.zup.order.controller.response.OrderResponse;
import br.com.zup.order.dto.OrderItemDto;
import br.com.zup.order.entity.Order;
import br.com.zup.order.event.OrderCreatedEvent;
import br.com.zup.order.repository.OrderRepository;
import br.com.zup.order.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;
    private KafkaTemplate<String, OrderCreatedEvent> template;

    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository, KafkaTemplate<String, OrderCreatedEvent> template) {
        this.orderRepository = orderRepository;
        this.template = template;
    }

    @Override
    public String save(CreateOrderRequest request) {
        String orderId = this.orderRepository.save(request.toEntity()).getId();
        OrderItemDto itemDto  = new OrderItemDto();
        OrderCreatedEvent event = new OrderCreatedEvent(
                orderId,
                request.getCustomerId(),
                request.getAmount(),
                request.getItems()
                        .stream()
                        .map(item -> {itemDto.setItem(item.getId());
                                    itemDto.setQuantity(item.getQuantity());
                                    return itemDto;
                                }
                        ).collect(Collectors.toList()));



        this.template.send("created-orders", event);

        return orderId;
    }

    @Override
    public List<OrderResponse> findAll() {
        return this.orderRepository.findAll()
                .stream()
                .map(OrderResponse::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public void orderInventoryReject(OrderCreatedEvent orderCreatedEvent){

        Optional<Order> orderReject = orderRepository.findById(orderCreatedEvent.getOrderId());
        orderReject.get().setStatus("inventory-reject");

        this.orderRepository.save(orderReject.get());

    }

    @Override
    public void paymentApproved(OrderCreatedEvent orderCreatedEvent){

        Optional<Order> orderApproved = orderRepository.findById(orderCreatedEvent.getOrderId());
        orderApproved.get().setStatus("payment-approved");

        this.orderRepository.save(orderApproved.get());

    }

    @Override
    public void paymentReject(OrderCreatedEvent orderCreatedEvent){

        Optional<Order> orderApproved = orderRepository.findById(orderCreatedEvent.getOrderId());
        orderApproved.get().setStatus("payment-reject");

        this.orderRepository.save(orderApproved.get());

    }
}
