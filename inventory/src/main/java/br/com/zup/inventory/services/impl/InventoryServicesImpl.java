package br.com.zup.inventory.services.impl;

import br.com.zup.inventory.controller.request.CreateInventoryRequest;
import br.com.zup.inventory.controller.response.InventoryResponse;
import br.com.zup.inventory.entity.Inventory;
import br.com.zup.inventory.event.OrderCreatedEvent;
import br.com.zup.inventory.repository.InventoryRepository;
import br.com.zup.inventory.services.InventoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class InventoryServicesImpl implements InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    private KafkaTemplate<String, OrderCreatedEvent> template;

    @Autowired
    public InventoryServicesImpl(InventoryRepository inventoryRepository, KafkaTemplate<String, OrderCreatedEvent> template) {
        this.inventoryRepository = inventoryRepository;
        this.template = template;
    }


    @Override
    public String save(CreateInventoryRequest request) {
        String inventoryId = this.inventoryRepository.save(request.toEntity()).getId();

        return inventoryId;
    }

    @Override
    public List<InventoryResponse> findAll() {
        return this.inventoryRepository.findAll()
                .stream()
                .map(InventoryResponse::fromEntity)
                .collect(Collectors.toList());
    }
    @Override
    public void validOrder(OrderCreatedEvent request) {

        for (int numLine = 0; numLine < request.getItemIds().size(); numLine++ ){

             Inventory inventoryBalance = inventoryRepository.findByIdItem(request.getItemIds().get(numLine).getItem());
             if (inventoryBalance == null){
                 this.template.send("reject-inventory", request);
             }else if(inventoryBalance.getQuantity() <= 0 || inventoryBalance.getQuantity() < request.getItemIds().get(numLine).getQuantity() ) {
                 this.template.send("reject-inventory", request);
             } else if(inventoryBalance.getQuantity() >= request.getItemIds().get(numLine).getQuantity()){
                 this.template.send("approved-inventory", request);
             }


        }





    }
}
