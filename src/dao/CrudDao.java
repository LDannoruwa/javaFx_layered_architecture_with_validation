package dao;

import java.util.ArrayList;

import entity.SuperEntity;

public interface CrudDao <T extends SuperEntity, ID> extends SuperDao {

    public boolean save(T t) throws Exception;
    public boolean delete(ID id) throws Exception;
    public boolean update(T t) throws Exception;
    public T get(ID id) throws Exception;
    public ArrayList<T> getAll() throws Exception;
}
