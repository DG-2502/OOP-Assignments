package domain;

public class Disc extends Publication{
    public final String narrator;

    public Disc(int year, String author, String title, String narrator) {
        super(year, author, title);
        this.narrator = narrator;
    }

    public String getNarrator() {
        return narrator;
    }
}
