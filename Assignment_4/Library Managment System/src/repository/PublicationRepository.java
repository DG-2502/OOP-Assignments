package repository;


public interface PublicationRepository<T> {
    void add(T publication);

    void print();
}
