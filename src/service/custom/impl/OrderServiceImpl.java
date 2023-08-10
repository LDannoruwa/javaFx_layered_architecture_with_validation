package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.OrderDao;
import db.DbConnection;
import dto.OrderDetailDto;
import dto.OrderDto;
import entity.Order;
import service.ServiceFactory;
import service.custom.ItemService;
import service.custom.OrderDetailService;
import service.custom.OrderService;

public class OrderServiceImpl implements OrderService{

    OrderDao orderDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDER);
    OrderDetailService orderDetailService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ORDER_DETAIL);
    ItemService itemService = ServiceFactory.getInstance().getService(ServiceFactory.ServiceType.ITEM);

    @Override
    public boolean saveOrder(OrderDto dto, ArrayList<OrderDetailDto> orderDetailDTOs) throws Exception {
        // transaction
        try {
            DbConnection.getInstance().getConnection().setAutoCommit(false);

            boolean isSaved = orderDao.save(new Order(dto.getOrderID(), dto.getOrderDate(), dto.getCustomerID()));
            boolean isDetailsSaved = orderDetailService.saveOrderDetail(orderDetailDTOs);
            boolean isUpdated = itemService.updateWhenOrder(orderDetailDTOs);

            if (isSaved && isDetailsSaved && isUpdated) {
                DbConnection.getInstance().getConnection().commit();
                return true;
            } else {
                DbConnection.getInstance().getConnection().rollback();
                return false;
            }
        } catch (Exception e) {
            DbConnection.getInstance().getConnection().rollback();
            return false;
        } finally {
            DbConnection.getInstance().getConnection().setAutoCommit(true);
        }
    }

}
