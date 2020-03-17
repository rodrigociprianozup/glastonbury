package br.com.zup.inventory.controller.response;

import br.com.zup.inventory.entity.Inventory;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

public class InventoryResponse {

    private String id;

    private String idItem;

    private BigDecimal amount;

    private Integer quantity;

    public InventoryResponse() {
    }

    public InventoryResponse(String id, String itemId, BigDecimal amount, Integer quantity) {
        this.id = id;
        this.amount = amount;
        this.idItem = itemId;
        this.quantity = quantity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getIdItem() {
        return idItem;
    }

    public void setIdItem(String idItem) {
        this.idItem = idItem;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public static InventoryResponse fromEntity(Inventory inventory) {
        return new InventoryResponse(
                inventory.getId(),
                inventory.getIdItem(),
                inventory.getAmount(),
                inventory.getQuantity()
        );
    }
}
