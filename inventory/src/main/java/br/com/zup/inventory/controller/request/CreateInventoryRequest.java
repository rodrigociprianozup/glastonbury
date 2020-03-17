package br.com.zup.inventory.controller.request;


import java.math.BigDecimal;
import java.util.UUID;
import br.com.zup.inventory.entity.Inventory;

public class CreateInventoryRequest {


    private String idItem;

    private BigDecimal amount;

    private Integer quantity;

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
    public Inventory toEntity() {
        return new Inventory(
                UUID.randomUUID().toString(),
                this.idItem,
                this.amount,
                this.quantity
        );
    }
}
