package domain.publications;

public class Magazine extends Publication{
    public Magazine(int year, String author, String title) {
        super(year, author, title);
    }

    public Magazine() {
        super("Magazine", "Magazine");
    }

    @Override
    public Publication clone() {
        return new Magazine(getYear(), getAuthor(), getTitle());
    }
}
