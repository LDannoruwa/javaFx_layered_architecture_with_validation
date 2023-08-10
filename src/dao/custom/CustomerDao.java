package dao.custom;

import java.util.ArrayList;

import dao.CrudDao;
import entity.Customer;

public interface CustomerDao extends CrudDao<Customer, Integer> {
    //You can add custom methods here. (Except CRUD)
    public ArrayList<Customer> getCustomerID() throws Exception;
}
