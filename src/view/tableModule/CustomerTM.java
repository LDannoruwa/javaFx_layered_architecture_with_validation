package view.tableModule;

import javafx.scene.control.Button;

public class CustomerTM {
    private int customer_id;
    private String customer_name;
    private String customer_address;
    private double customer_Salary;

    private Button btnDelete;

    
    public CustomerTM() {
    }

      
    public CustomerTM(int customer_id, String customer_name, String customer_address, double customer_Salary,
            Button btnDelete) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_Salary = customer_Salary;
        this.btnDelete = btnDelete;
    }


    public int getCustomer_id() {
        return customer_id;
    }

    public void setCustomer_id(int customer_id) {
        this.customer_id = customer_id;
    }

    public String getCustomer_name() {
        return customer_name;
    }

    public void setCustomer_name(String customer_name) {
        this.customer_name = customer_name;
    }

    public String getCustomer_address() {
        return customer_address;
    }

    public void setCustomer_address(String customer_address) {
        this.customer_address = customer_address;
    }

    public double getCustomer_Salary() {
        return customer_Salary;
    }

    public void setCustomer_Salary(double customer_Salary) {
        this.customer_Salary = customer_Salary;
    }

    public Button getBtnDelete() {
        return btnDelete;
    }

    public void setBtnDelete(Button btnDelete) {
        this.btnDelete = btnDelete;
    }

    @Override
    public String toString() {
        return "CustomerTM [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_address="
                + customer_address + ", customer_Salary=" + customer_Salary + ", btnDelete=" + btnDelete + "]";
    }    
}
