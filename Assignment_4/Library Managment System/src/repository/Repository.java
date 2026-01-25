package repository.publications;


public interface PublicationRepository<T> {
    void add(T publication);
    void print();
    int size();
}
