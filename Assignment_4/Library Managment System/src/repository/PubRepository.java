package repository;

import domain.publications.Publication;

import java.util.ArrayList;

public class PubRepository implements Repository<Publication> {
    private final ArrayList<Publication> publications = new ArrayList<>();

    @Override
    public void add(Publication pub) {
        publications.add(pub);
    }

    public void print(){
        for (Publication publication : publications){
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
