package dto;

public class CustomerDto {
    private int customer_id;
    private String customer_name;
    private String customer_address;
    private double customer_Salary;

    
    public CustomerDto() {
    }
    
    public CustomerDto(int customer_id) {
        this.customer_id = customer_id;
    }

    public CustomerDto(int customer_id, String customer_name, String customer_address, double customer_Salary) {
        this.customer_id = customer_id;
        this.customer_name = customer_name;
        this.customer_address = customer_address;
        this.customer_Salary = customer_Salary;
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

    @Override
    public String toString() {
        return "CustomerDto [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_address="
                + customer_address + ", customer_Salary=" + customer_Salary + "]";
    }   
}
