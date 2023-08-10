package dao.custom.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.CustomerDao;
import entity.Customer;

public class CustomerDaoImpl implements CustomerDao {

    @Override
    public boolean save(Customer customer) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("INSERT INTO Customer VALUES (?,?,?,?)",
                customer.getCustomer_id(), customer.getCustomer_name(), customer.getCustomer_address(),
                customer.getCustomer_Salary());
    }

    @Override
    public boolean delete(Integer id) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("DELETE FROM Customer WHERE id=?", id);
    }

    @Override
    public boolean update(Customer customer) throws ClassNotFoundException, SQLException {
        return CrudUtil.execute("UPDATE Customer SET name=?, address=?, salary=? WHERE id=?",
                customer.getCustomer_name(), customer.getCustomer_address(),
                customer.getCustomer_Salary(), customer.getCustomer_id());
    }

    @Override
    public Customer get(Integer id) throws ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer WHERE id=?", id);
        if (set.next()) {
            return new Customer(set.getInt(1), set.getString(2),
                    set.getString(3), set.getDouble(4));
        }
        return null;
    }

    @Override
    public  ArrayList<Customer> getAll() throws ClassNotFoundException, SQLException {
        ResultSet set = CrudUtil.execute("SELECT * FROM Customer");
        ArrayList<Customer> customerList = new ArrayList<>();

        while (set.next()) {
            customerList.add(new Customer(
                    set.getInt(1), set.getString(2),
                    set.getString(3), set.getDouble(4)));
        }

        return customerList;
    }

    //custome method in CustomerDao.java
    @Override
    public ArrayList<Customer> getCustomerID() throws ClassNotFoundException, SQLException{
        ResultSet set = CrudUtil.execute("SELECT id FROM Customer");
        ArrayList<Customer> customerIDList = new ArrayList<>();

        while (set.next()) {
            customerIDList.add(new Customer(set.getInt(1)));
        }

        return customerIDList;
    }    
}
