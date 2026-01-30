package domain.human;


public class NoLibrarian extends Librarian {
    public NoLibrarian(int id) {
        super(id);
    }

    @Override
    public String toString() {
        return "No Librarian with ID: " + super.getId();
    }
}
