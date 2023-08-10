package dao.custom.impl;

import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.OrderDao;
import entity.Order;

public class OrderDaoImpl implements OrderDao{

    @Override
    public boolean save(Order order) throws Exception {
        return CrudUtil.execute("INSERT INTO Orders VALUES (?,?,?)", order.getOrderID(), order.getOrderDate(),
                order.getCustomerID());
    }

    @Override
    public boolean delete(String id) throws Exception {
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public boolean update(Order order) throws Exception {
        return false;
    }

    @Override
    public Order get(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<Order> getAll() throws Exception {
        return null;
    }
}
