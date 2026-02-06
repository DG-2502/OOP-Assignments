package domain.user;

public class Librarian extends User {

    public Librarian() {
        super();
    }

    public Librarian(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String toString() {
        return super.toString() + " (Librarian)";
    }
}
