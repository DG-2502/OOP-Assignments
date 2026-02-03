package domain.publications;

public class Book extends Publication {
    private final int pages;

    public Book() {
        super("Book", "Book");
        this.pages = 100;
    }

    public Book(int year, String author, String title, int pages) {
        super(year, author, title);
        this.pages = pages;
    }

    @Override
    public String toString() {
        return super.toString() + " " + pages;
    }

    @Override
    public Publication clone() {
        return new Book(getYear(), getAuthor(), getTitle(), this.pages);
    }
}
