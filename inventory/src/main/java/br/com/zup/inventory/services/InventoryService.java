package br.com.zup.inventory.services;

import br.com.zup.inventory.controller.request.CreateInventoryRequest;
import br.com.zup.inventory.controller.response.InventoryResponse;
import br.com.zup.inventory.event.OrderCreatedEvent;

import java.util.List;

public interface InventoryService {


    public void validOrder(OrderCreatedEvent request);

    String save(CreateInventoryRequest request);

    List<InventoryResponse> findAll();
}
