package domain.publications;

public class Book extends Publication {
    private final int pages;

    public Book(String author, String title) {
        super(author, title);
        this.pages = 20 + ((int) (Math.random() * 200));
    }

    public Book() {
        super("Book", "Book");
        this.pages = 100;
    }

    public Book(int year, String author, String title, int pages) {
        super(year, author, title);
        this.pages = pages;
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return super.toString() + " " + pages;
    }

    @Override
    public boolean compare(Publication p) {
        if (p instanceof Book comp) {
            return super.compare(p) || comp.getPages() == pages;
        }
        return super.compare(p);
    }

    @Override
    public Publication clone() {
        return new Book(getYear(), getAuthor(), getTitle(), this.pages);
    }
}
