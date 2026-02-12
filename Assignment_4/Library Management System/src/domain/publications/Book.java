package domain.publications;

public class Book extends Publication {
    private final int pages;

    public Book(String author, String title) {
        super(author, title, "book");
        this.pages = 20 + ((int) (Math.random() * 200));
    }

    public Book() {
        super("Book", "Book", "book");
        this.pages = 100;
    }

    public Book(int year, String author, String title, int pages) {
        super(year, author, title, "book");
        this.pages = pages;
    }
    private Book(int year, String author, String title, int pages, int id) {
        super(year, author, title, id, "book");
        this.pages = pages;
    }

    public Book(String title, String author, String year, String amount, String pages) {
        super(title, author, year, amount, "book");
        this.pages = Integer.parseInt(pages);
    }

    public int getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return super.toString() + " " + pages;
    }

//    @Override
//    public boolean compare(Publication p) {
//        if (p instanceof Book comp) {
//            return super.compare(p) || comp.getPages() == pages;
//        }
//        return super.compare(p);
//    }

    @Override
    public Publication clone() {
        return new Book(getYear(), getAuthor(), getTitle(), this.pages, getId());
    }

    public static Publication create(String[] info) {
        return new Book(info[0], info[1], info[2], info[3], info[4]);
    }
}
