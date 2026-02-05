package repository;

import domain.human.Librarian;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class LibrarianRepository implements Repository<Librarian> {
    private final ArrayList<Librarian> librarians = new ArrayList<>(){{
        add(new Librarian("Rosemary", "Mendez"));
        add(new Librarian("Lindsey", "Stanley"));
        add(new Librarian("Carley", "Robinson"));
        add(new Librarian("Gustavo", "Vaughan"));
    }
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

    @Override
    public void print() {
//        for (Librarian librarian : librarians){
//            System.out.println(librarians.indexOf(librarian) + " " + librarian);
//        }
    }

    @Override
    public Librarian getByID(int id) {
        return null;
    }

    @Override
    public ArrayList<Librarian> find(String name) {
        return (ArrayList<Librarian>) librarians.stream().filter(librarian -> librarian.getFirstName().equalsIgnoreCase(name) || librarian.getLastName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }
}
