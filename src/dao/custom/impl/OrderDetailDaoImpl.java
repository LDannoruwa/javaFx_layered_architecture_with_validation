package dao.custom.impl;

import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.OrderDetailDao;
import entity.OrderDetail;

public class OrderDetailDaoImpl implements OrderDetailDao {

    @Override
    public boolean save(OrderDetail orderDetail) throws Exception {
        return CrudUtil.execute("INSERT INTO OrderDetail VALUES (?,?,?,?)", orderDetail.getOrderId(), orderDetail.getID(), orderDetail.getQty(),
                orderDetail.getUnitPrice());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return false;
    }

    @Override
    public boolean update(OrderDetail orderDetail) throws Exception {
        return false;
    }

    @Override
    public OrderDetail get(String id) throws Exception {
        return null;
    }

    @Override
    public ArrayList<OrderDetail> getAll() throws Exception {
        return null;
    }
}
