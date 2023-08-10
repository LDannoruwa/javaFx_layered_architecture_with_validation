package dto;

public class OrderDetailDto {
    private String orderId;
    private String id;
    private double qty;
    private double unitPrice;

    public OrderDetailDto() {
    }

    public OrderDetailDto(String orderId, String id, double qty, double unitPrice) {
        this.orderId = orderId;
        this.id = id;
        this.qty = qty;
        this.unitPrice = unitPrice;
    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public String getID() {
        return id;
    }

    public void setID(String id) {
        this.id = id;
    }

    public double getQty() {
        return qty;
    }

    public void setQty(double qty) {
        this.qty = qty;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public void setUnitPrice(double unitPrice) {
        this.unitPrice = unitPrice;
    }    
}
