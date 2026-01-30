package repository;

import domain.publications.Publication;

import java.util.ArrayList;

public class PublicationRepository implements Repository<Publication> {
    private final ArrayList<Publication> publications = new ArrayList<>();

    @Override
    public void add(Publication pub) {
        publications.add(pub);
    }

    public void print(){
        for (Publication publication : publications){
            System.out.println(publication);
        }
    }
    public int size(){
        return publications.size();
    }

    @Override
    public void remove(int i) {
        publications.remove(i);
    }

    @Override
    public boolean has(Publication publication) {
        return publications.contains(publication);
    }

    public void remove(Publication publication){
        publications.remove(publication);
    }

    @Override
    public String toString() {
        if (publications.isEmpty()){
            return "    No publications";
        }
        String string = "    ";
        for (Publication publication : publications){
            string = string.concat("    " + publication.toString());
        }
        return string;
    }

    public ArrayList<Publication> getPublications() {
        return publications;
    }
}
