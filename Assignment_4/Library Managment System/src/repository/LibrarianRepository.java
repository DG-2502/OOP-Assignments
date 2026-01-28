package repository;

import domain.human.Librarian;
import domain.human.NoLibrarian;

import java.util.ArrayList;

public class LibrarianRepository implements Repository<Librarian> {
    private final ArrayList<Librarian> librarians = new ArrayList<>(){
    };

    public void add(Librarian librarian){
        librarians.add(librarian);
    }

    @Override
    public void remove(int i) {
        librarians.remove(i);
    }

    @Override
    public boolean has(Librarian librarian) {
        return librarians.contains(librarian);
    }

    public Librarian getById(int id) {
        try {
            return librarians.stream().filter(s -> s.getId() == id).findFirst().get();
        } catch (Exception e) {
            return new NoLibrarian(id);
        }
    }

    @Override
    public void print() {

    }
}
