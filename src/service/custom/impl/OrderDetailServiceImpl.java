package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.OrderDetailDao;
import dto.OrderDetailDto;
import entity.OrderDetail;
import service.custom.OrderDetailService;

public class OrderDetailServiceImpl implements OrderDetailService{

    OrderDetailDao orderDetailDAO = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ORDER_DETAIL);

    @Override
    public boolean saveOrderDetail(ArrayList<OrderDetailDto> dtoList) throws Exception {
        for (OrderDetailDto dto : dtoList) {
            boolean isSaved = saveOrderDetail(dto);
            if (!isSaved) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean saveOrderDetail(OrderDetailDto dto) throws Exception {
        return orderDetailDAO.save(new OrderDetail(dto.getOrderId(), dto.getID(), dto.getQty(), dto.getUnitPrice()));
    }
}
