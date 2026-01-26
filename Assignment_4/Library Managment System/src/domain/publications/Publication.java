package domain.publications;

public abstract class Publication {
    private final int year;
    private final String author;
    private final String title;

    public Publication() {
        this.year = 0;
        this.author = "None";
        this.title = "None";
    }

    public Publication(int year, String author, String title) {
        this.year = year;
        this.author = author;
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return author + " " + year + " " + title;
    }
}
