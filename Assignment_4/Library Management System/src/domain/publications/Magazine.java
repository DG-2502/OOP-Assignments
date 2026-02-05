package domain.publications;

public class Magazine extends Publication{
    public Magazine(String author, String title) {
        super(author, title);
    }

    public Magazine(int year, String author, String title) {
        super(year, author, title);
    }

    public Magazine() {
        super("Magazine", "Magazine");
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
        return new Magazine(getYear(), getAuthor(), getTitle());
    }
}
