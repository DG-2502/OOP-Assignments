package domain.publications;

import domain.Entity;

public abstract class Publication extends Entity {
    private final int year;
    private final String author;
    private final String title;
    private int amount;
    private final PubType pubType;

    public Publication(String author, String title, String type){
        super();
        this.year = 1900 + ((int) (Math.random() * 126));
        this.author = author;
        this.title = title;
        this.amount = ((int) (Math.random() * 5));
        this.pubType = PubType.valueOf(type);
    }

    public Publication(int year, String author, String title, String type) {
        super();
        this.year = year;
        this.author = author;
        this.title = title;
        this.amount = 1;
        this.pubType = PubType.valueOf(type);

    }
    public Publication(int year, String author, String title, int id, String type) {
        super(id);
        this.year = year;
        this.author = author;
        this.title = title;
        this.amount = 1;
        this.pubType = PubType.valueOf(type);

    }
    public Publication(String title, String author, String year, String amount, String type) {
        super();
        this.title = title;
        this.author = author;
        this.year = Integer.parseInt(year);
        this.amount = Integer.parseInt(amount);
        this.pubType = PubType.valueOf(type);
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

//    public boolean compare(Publication p) {
//        return  this.title.equals(p.getTitle()) &&
//                this.author.equals(p.getAuthor()) &&
//                this.year == p.getYear();
//    }

    public abstract Publication clone();

    @Override
    public String getType() {
        return "";
    }

    public static Publication createByType(PubType type, String[] info) {
        if (PubType.values()[0].equals(type)) {
            return Book.create(info);
        } else if (PubType.values()[1].equals(type)) {
            return Disc.create(info);
        } else if (PubType.values()[2].equals(type)) {
            return Magazine.create(info);
        }
        return null;
    }

    public enum PubType {
        book,
        disc,
        magazine;
    }
}
