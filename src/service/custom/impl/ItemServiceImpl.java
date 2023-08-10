package service.custom.impl;

import java.util.ArrayList;

import dao.DaoFactory;
import dao.custom.ItemDao;
import dto.ItemDto;
import dto.OrderDetailDto;
import entity.Item;
import service.custom.ItemService;

public class ItemServiceImpl implements ItemService {
    private ItemDao itemDao = DaoFactory.getInstance().getDao(DaoFactory.DAOType.ITEM);

    @Override
    public boolean saveItem(ItemDto itemDto) throws Exception {
        Item item = new Item(itemDto.getItem_id(), itemDto.getItem_description(), itemDto.getItem_unitPrice(), itemDto.getItem_QOH());
        return itemDao.save(item);
    }

    @Override
    public boolean updateItem(ItemDto itemDto) throws Exception {
        Item item = new Item(itemDto.getItem_id(), itemDto.getItem_description(), itemDto.getItem_unitPrice(), itemDto.getItem_QOH());
        return itemDao.update(item);
    }

    @Override
    public boolean deleteItem(String id) throws Exception {
        return itemDao.delete(id);
    }

    @Override
    public ItemDto getItem(String id) throws Exception {
        Item item = itemDao.get(id);
        if (item != null) {
            return new ItemDto(item.getItem_id(), item.getItem_description(), item.getItem_unitPrice(), item.getItem_QOH());
        }
        return null;
    }

    @Override
    public ArrayList<ItemDto> getAllItem() throws Exception {
        ArrayList<Item> list = itemDao.getAll();
        ArrayList<ItemDto> dtoList = new ArrayList<>();

        for (Item i : list) {
            dtoList.add(new ItemDto(i.getItem_id(), i.getItem_description(), i.getItem_unitPrice(), i.getItem_QOH()));
        }
        return dtoList;
    }

    @Override
    public ArrayList<ItemDto> getAllItemId() throws Exception {
        ArrayList<Item> list = itemDao.getItemId();
        ArrayList<ItemDto> dtoList = new ArrayList<>();

        for (Item i : list) {
            dtoList.add(new ItemDto(i.getItem_id()));
        }
        return dtoList;
    }

    @Override
    public boolean updateWhenOrder(ArrayList<OrderDetailDto> orderDetailDTOs) throws Exception {
        for (OrderDetailDto orderDetailDTO : orderDetailDTOs) {
            boolean isUpdated = updateWhenOrder(orderDetailDTO);
            if (!isUpdated) {
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean updateWhenOrder(OrderDetailDto orderDetailDTOs) throws Exception {
        return itemDao.updateWhenOrder(new Item(orderDetailDTOs.getID(), orderDetailDTOs.getQty()));
    }
}
