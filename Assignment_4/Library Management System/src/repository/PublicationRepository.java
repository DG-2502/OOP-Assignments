package repository;

import domain.publications.Book;
import domain.publications.Disc;
import domain.publications.Magazine;
import domain.publications.Publication;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PublicationRepository implements Repository<Publication> {
    private final ArrayList<Publication> publications = new ArrayList<>(){};

    @Override
    public void add(Publication pub) {
        publications.add(pub);
        if (!has(pub)) {
            this.publications.add(pub);
        } else {
            getByID(pub.getTitle()).increase();
        }
    }

    public void add(String title) {
        if (has(title)){
            getByID(title).increase();
        }
        else {
            System.out.println("There is no publication registered with such title: " + title);
        }
    }

    public void addNew(Publication publication){
        if (has(publication)) {
            getPublication(publication).increase();
        }
        else {
            publications.add(publication);
        }
    }

    public void print(){
        for (Publication publication : publications) {
            System.out.println(publications.indexOf(publication) + " " +  publication);
        }
    }
    public int size() {
        return publications.size();
    }

    @Override
    public void remove(int i) {
        publications.remove(i);
    }

    @Override
    public boolean has(Publication publication) {
        return publications.stream().anyMatch(publication1 -> publication1.compare(publication));
    }

    private Publication getPublication(Publication publication) {
        return publications.stream().filter(publication1 -> publication1.compare(publication)).findFirst().get();
    }

    public boolean has(String title){
        return publications.stream().anyMatch(publication -> publication.getTitle().equals(title));
    }

    @Override
    public String toString() {
        if (publications.isEmpty()) {
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

    public Publication getByID(String title) {
       return publications.stream().filter(publication -> publication.getTitle().equals(title)).findFirst().get();
    }

    @Override
    public Publication getByID(int id) {
        try {
            return publications.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Publication> find(String title) {
        return (ArrayList<Publication>) publications.stream().filter(publication -> publication.getTitle().equals(title)).collect(Collectors.toList());
    }
}
