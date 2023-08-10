package service.custom;

import java.util.ArrayList;

import dto.OrderDetailDto;

public interface OrderDetailService {
    public boolean saveOrderDetail(ArrayList<OrderDetailDto> dto) throws Exception;

    public boolean saveOrderDetail(OrderDetailDto dto) throws Exception;
}
