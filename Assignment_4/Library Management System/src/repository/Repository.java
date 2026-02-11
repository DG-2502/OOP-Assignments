package repository;


import java.util.ArrayList;


public interface Repository<T> {
    void add(T t);
    void remove(int id);
    boolean hasId(int id);
    ArrayList<T> getAll();
    T getByID(int id);
    ArrayList<T> find(String string);
}
