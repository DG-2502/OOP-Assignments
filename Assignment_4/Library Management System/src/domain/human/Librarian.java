package domain.human;

public class Librarian extends Human {

    public Librarian() {
        super();
    }

    public Librarian(String firstName, String lastName) {
        super(firstName, lastName);
    }

    @Override
    public String toString() {
        return "Librarian " + super.toString();
    }
}
