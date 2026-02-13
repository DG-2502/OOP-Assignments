package domain.publications;

public class Book extends Publication {
    private String pages;

    public Book(String author, String title) {
        super(author, title, "book");
        this.pages = String.valueOf(20 + ((int) (Math.random() * 200)));
    }

    public Book() {
        super("Book", "Book", "book");
        this.pages = "100";
    }

    public Book(String year, String author, String title, String pages) {
        super(year, author, title, "book");
        this.pages = pages;
    }

    private Book(String year, String author, String title, String pages, int id) {
        super(year, author, title, id, "book");
        this.pages = pages;
    }

    public Book(String title, String author, String year, String amount, String pages) {
        super(title, author, year, amount, "book");
        this.pages = pages;
    }

    public String getPages() {
        return pages;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + pages + "p.";
    }

    @Override
    public boolean equals(Publication p) {
        if (p instanceof Book comp) {
            return super.equals(p) & comp.getPages().equals(pages);
        }
        return false;
    }

    @Override
    public Publication clone() {
        return new Book(getYear(), getAuthor(), getTitle(), this.pages, getId());
    }

    public static Publication create(String[] info) {
        return new Book(info[0], info[1], info[2], info[3], info[4]);
    }

    @Override
    public void update(String[] info) {
        super.update(info);
        String newPages = info[4];
        if (!newPages.isEmpty()) {
            this.pages = newPages;
        }
    }
}
