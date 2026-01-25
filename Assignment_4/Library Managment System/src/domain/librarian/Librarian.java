package domain.librarian;

import repository.library.Library;

public class Librarian {
    private static int ID = 0;
    private final int id;
    private final Library library;


    public Librarian(Library library) {
        this.id = ID++;
        this.library = library;
    }

    public Librarian(int id) {
        this.id = id;
        this.library = null;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "ID: " + id;
    }
}
