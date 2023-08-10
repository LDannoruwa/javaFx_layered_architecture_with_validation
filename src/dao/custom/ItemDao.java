package dao.custom;

import java.util.ArrayList;

import dao.CrudDao;
import entity.Item;


public interface ItemDao extends CrudDao<Item, String> {

    public ArrayList<Item> getItemId() throws Exception;

    public boolean updateWhenOrder(Item item) throws Exception;
}
