package domain.human;

public class Librarian extends Human {
    private static int ID = 0;
    private int id;

    public Librarian() {
        super();
        this.id = ID++;
    }

    protected Librarian(int id) {
        this();
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString() + " " + id;
    }
}
