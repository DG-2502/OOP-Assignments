package domain;

public abstract class Publication {
    private int year;
    private String author;
    private String title;

    public Publication(int year, String author, String title) {
        this.year = year;
        this.author = author;
        this.title = title;
    }

    public String getTitle(){
        return title;
    };

    public int getYear() {
        return year;
    }

    public String getAuthor() {
        return author;
    }
}
