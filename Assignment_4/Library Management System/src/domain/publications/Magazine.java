package domain.publications;

public class Magazine extends Publication{
    public Magazine(String author, String title) {
        super(author, title);
    }
    public Magazine() {
        super("Magazine", "Magazine");
    }
    public Magazine(int year, String author, String title) {
        super(year, author, title);
    }
    private Magazine(int year, String author, String title, int id) {
        super(year, author, title, id);
    }

    public Magazine(String title, String author, String year, String amount) {
        super(title, author, year, amount);
    }

    @Override
    public boolean compare(Publication p) {
        if (p instanceof Magazine comp) {
            return super.compare(p);
        }
        return super.compare(p);
    }

    @Override
    public Publication clone() {
        return new Magazine(getYear(), getAuthor(), getTitle(), getId());
    }
}
