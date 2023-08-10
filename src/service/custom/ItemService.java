package service.custom;

import java.util.ArrayList;

import dto.ItemDto;
import dto.OrderDetailDto;

public interface ItemService {
    public boolean saveItem(ItemDto itemDto) throws Exception;

    public boolean updateItem(ItemDto itemDto) throws Exception;

    public boolean deleteItem(String id) throws Exception;

    public ItemDto getItem(String id) throws Exception;

    public ArrayList<ItemDto> getAllItem() throws Exception;

    public ArrayList<ItemDto> getAllItemId() throws Exception;

    public boolean updateWhenOrder(ArrayList<OrderDetailDto> orderDetailDTOs) throws Exception;

    public boolean updateWhenOrder(OrderDetailDto orderDetailDTOs) throws Exception;
}
