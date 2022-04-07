package dao;

public interface BaseDao<Key, Type>{
    public Type find(Key id);
    public boolean add(Type value);
    public boolean update(Type value, Key id);
    public boolean delete(Key id);
}
