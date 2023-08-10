package service.custom;

import java.util.ArrayList;

import dto.OrderDetailDto;
import dto.OrderDto;

public interface OrderService {
    public boolean saveOrder(OrderDto dto, ArrayList<OrderDetailDto> orderDetailDTOs) throws Exception;
}
