package domain.publications;

public class Book extends Publication {
    private final int pages;

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
}
