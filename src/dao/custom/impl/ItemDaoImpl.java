package dao.custom.impl;

import java.sql.ResultSet;
import java.util.ArrayList;

import dao.CrudUtil;
import dao.custom.ItemDao;
import entity.Item;

public class ItemDaoImpl implements ItemDao {

    @Override
    public boolean save(Item item) throws Exception {
        return CrudUtil.execute("INSERT INTO Item VALUES (?,?,?,?)",
                item.getItem_id(), item.getItem_description(), item.getItem_unitPrice(), item.getItem_QOH());
    }

    @Override
    public boolean delete(String id) throws Exception {
        return CrudUtil.execute("DELETE FROM Item WHERE id=?", id);
    }

    @Override
    public boolean update(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET description = ? , unitPrice = ? , qoh = ? WHERE id = ?",
                item.getItem_description(), item.getItem_unitPrice(), item.getItem_QOH(), item.getItem_id());
    }

    @Override
    public Item get(String id) throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item WHERE id=?", id);
        if (set.next()) {
            return new Item(set.getString(1), set.getString(2),
                    set.getDouble(3), set.getDouble(4));
        }
        return null;
    }

    @Override
    public ArrayList<Item> getAll() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT * FROM Item");
        ArrayList<Item> itemList = new ArrayList<>();

        while (set.next()) {
            itemList.add(new Item(
                    set.getString(1), set.getString(2),
                    set.getDouble(3), set.getDouble(4)));
        }
        return itemList;
    }

    @Override
    public ArrayList<Item> getItemId() throws Exception {
        ResultSet set = CrudUtil.execute("SELECT id FROM Item");
        ArrayList<Item> itemList = new ArrayList<>();

        while (set.next()) {
            itemList.add(new Item(set.getString(1)));
        }
        return itemList;
    }

    @Override
    public boolean updateWhenOrder(Item item) throws Exception {
        return CrudUtil.execute("UPDATE Item SET  qoh = (qoh - ? ) WHERE id = ?",
        item.getItem_QOH(), item.getItem_id());
    }

}
