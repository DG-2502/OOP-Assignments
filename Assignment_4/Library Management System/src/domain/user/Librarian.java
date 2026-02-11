package domain.user;

public class Librarian extends User {

    public Librarian() {
        super();
    }

    public Librarian(String firstName, String lastName) {
        super(firstName, lastName);
    }

    public Librarian(String firstName, String lastName, String age) {
        super(firstName, lastName, age);
    }

    @Override
    public String toString() {
        return super.toString() + " (Librarian)";
    }
}
