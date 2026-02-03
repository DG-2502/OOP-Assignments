package repository;

import domain.human.Librarian;
import domain.human.NoLibrarian;
import domain.human.Reader;

import java.util.ArrayList;
import java.util.stream.Collectors;


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
            return new NoLibrarian(id);        }
    }

    @Override
    public void print() {

    }

    @Override
    public Librarian getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Librarian> find(String name) {
        return (ArrayList<Librarian>) librarians.stream().filter(librarian -> librarian.getFirstName().equals(name)).collect(Collectors.toList());
    }
}
