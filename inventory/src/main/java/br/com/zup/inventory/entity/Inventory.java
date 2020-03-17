package br.com.zup.inventory.entity;

import org.hibernate.annotations.Cascade;
import org.hibernate.annotations.CascadeType;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.math.BigDecimal;

@Entity(name = "inventory")
public class Inventory {

    @Id
    private String id;

    private String idItem;

    private BigDecimal amount;

    private Integer quantity;

    public Inventory(){

    }

    public Inventory(String id,  String idItem, BigDecimal amount, Integer quantity) {
        this.id = id;
        this.idItem  = idItem;
        this.amount = amount;
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

}
