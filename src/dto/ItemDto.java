package dto;

public class ItemDto {
    private String item_id;
    private String item_description;
    private double item_unitPrice;
    private double item_QOH;

    public ItemDto() {
    }
    
    public ItemDto(String item_id) {
        this.item_id = item_id;
    }

    public ItemDto(String item_id, String item_description, double item_unitPrice, double item_QOH) {
        this.item_id = item_id;
        this.item_description = item_description;
        this.item_unitPrice = item_unitPrice;
        this.item_QOH = item_QOH;
    }

    public String getItem_id() {
        return item_id;
    }
    public void setItem_id(String item_id) {
        this.item_id = item_id;
    }
    public String getItem_description() {
        return item_description;
    }
    public void setItem_description(String item_description) {
        this.item_description = item_description;
    }
    public double getItem_unitPrice() {
        return item_unitPrice;
    }
    public void setItem_unitPrice(double item_unitPrice) {
        this.item_unitPrice = item_unitPrice;
    }
    public double getItem_QOH() {
        return item_QOH;
    }
    public void setItem_QOH(double item_QOH) {
        this.item_QOH = item_QOH;
    }
}
