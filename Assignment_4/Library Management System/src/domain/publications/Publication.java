package domain.publications;

import domain.Entity;

public abstract class Publication extends Entity {
    private final int year;
    private final String author;
    private final String title;
    private int amount;

    public Publication(String author, String title){
        super();
        this.year = 1900 + ((int) (Math.random() * 126));
        this.author = author;
        this.title = title;
        this.amount = ((int) (Math.random() * 5));
    }

    public Publication(int year, String author, String title) {
        super();
        this.year = year;
        this.author = author;
        this.title = title;
        this.amount = 1;
    }
    public Publication(int year, String author, String title, int id) {
        super(id);
        this.year = year;
        this.author = author;
        this.title = title;
        this.amount = 1;
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
        return super.toString() + author + " " + title + " " + year + " " + amount;
    }

    public void increase() {
        this.amount += 1;
    }

    public boolean decrease() {
        if (amount == 0) {
            return false;
        }
        this.amount -= 1;
        return true;
    }

    public boolean compare(Publication p) {
        return  this.title.equals(p.getTitle()) &&
                this.author.equals(p.getAuthor()) &&
                this.year == p.getYear();
    }

    public abstract Publication clone();
}
