package repository.librarian_repository;

import domain.librarian.Librarian;

import java.util.ArrayList;

public class LibrarianRepository {
    private final ArrayList<Librarian> librarians = new ArrayList<>(){
    };

    public void add(Librarian librarian){
        librarians.add(librarian);
    }

    public Librarian getById(int id) {
        return librarians.stream().filter(s -> s.getId() == id).findFirst().get();
    }
}
