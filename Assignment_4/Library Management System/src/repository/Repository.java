package repository;


import java.util.ArrayList;


public interface Repository<T> {
    void add(T t);
    void remove(int id);
//    boolean has(T t);
    boolean hasId(int id);
//    void print();
    ArrayList<T> getAll();
    T getByID(int id);
    ArrayList<T> find(String string);
}
