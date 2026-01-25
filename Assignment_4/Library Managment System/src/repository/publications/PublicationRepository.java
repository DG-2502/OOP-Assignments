package repository.publications;

import repository.Repository;

import java.util.ArrayList;

public class PublicationRepository<T> implements Repository<T> {
    private final ArrayList<T> publications = new ArrayList<>();

    @Override
    public void add(T t) {
        publications.add(t);
    }

    public void print(){
        for (T publication : publications){
            System.out.print(publication);
            System.out.println();
        }
    }
    public int size(){
        return publications.size();
    }

    @Override
    public void remove(int i) {
        publications.remove(i);
    }
}
