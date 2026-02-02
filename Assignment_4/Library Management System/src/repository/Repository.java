package repository;


import java.util.ArrayList;


public interface Repository<T> {
    void add(T t);
    void remove(int i);
    boolean has(T t);
    void print();
    T getByID(int id);
    ArrayList<T> find(String string);
}
