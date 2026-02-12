package domain.user;

public class Librarian extends User {

    public Librarian() {
        super("librarian");
    }

    public Librarian(String firstName, String lastName) {
        super(firstName, lastName, "librarian");
    }

    public Librarian(String firstName, String lastName, String age) {
        super(firstName, lastName, age, "librarian");
    }

    @Override
    public String toString() {
        return super.toString() + " (Librarian)";
    }

    public static User create(String[] info) {
        return new Librarian(info[0], info[1], info[2]);
    }
}
