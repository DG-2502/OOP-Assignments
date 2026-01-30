package repository;


public interface Repository<T> {
    void add(T t);
    void remove(int i);
    boolean has(T t);
    void print();
}
