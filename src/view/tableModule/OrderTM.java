package view.tableModule;

import javafx.scene.control.Button;

public class OrderTM {
    private String item_id;
    private String item_description;
    private double item_unitPrice;
    private double item_QOH;
    private double item_Qty;
    private double item_total;

    private Button btnRemove;

    public OrderTM() {
    }

    public OrderTM(String item_id, String item_description, double item_unitPrice, double item_QOH, double item_Qty,
            double item_total, Button btnRemove) {
        this.item_id = item_id;
        this.item_description = item_description;
        this.item_unitPrice = item_unitPrice;
        this.item_QOH = item_QOH;
        this.item_Qty = item_Qty;
        this.item_total = item_total;
        this.btnRemove = btnRemove;
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

    public double getItem_Qty() {
        return item_Qty;
    }

    public void setItem_Qty(double item_Qty) {
        this.item_Qty = item_Qty;
    }

    public double getItem_total() {
        return item_total;
    }

    public void setItem_total(double item_total) {
        this.item_total = item_total;
    }

    public Button getBtnRemove() {
        return btnRemove;
    }

    public void setBtnRemove(Button btnRemove) {
        this.btnRemove = btnRemove;
    }   
}
