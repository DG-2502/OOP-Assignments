package domain.publications;

public class Disc extends Publication{
    public final String narrator;

    public Disc() {
        super("Disc", "Disc");
        this.narrator = "None";
    }

    public Disc(int year, String author, String title, String narrator) {
        super(year, author, title);
        this.narrator = narrator;
    }

    @Override
    public String toString() {
        return super.toString() + " " + narrator;
    }

    @Override
    public Publication clone() {
        return new Disc(getYear(), getAuthor(), getTitle(), this.narrator);
    }
}
