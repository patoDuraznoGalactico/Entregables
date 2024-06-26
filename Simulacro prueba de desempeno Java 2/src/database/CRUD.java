package database;

import java.util.List;

public interface CRUD {
    public Object insert(Object obj);
    public List<Object> findAll();
    public Boolean update(Object obj);
    public Boolean delete(Object obj);

}
