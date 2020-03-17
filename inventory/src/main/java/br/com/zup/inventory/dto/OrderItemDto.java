package br.com.zup.inventory.dto;

public class OrderItemDto {

    private String item;
    private Integer quantity;

    public OrderItemDto(){}

    public OrderItemDto(String item, Integer quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public String getItem() {
        return item;
    }

    public void setItem(String item) {
        this.item = item;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }


}
