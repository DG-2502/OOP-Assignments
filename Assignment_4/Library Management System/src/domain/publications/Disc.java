package domain.publications;

public class Disc extends Publication {
    public String narrator;

    public Disc(String author, String title) {
        super(author, title, "disc");
        this.narrator = "None";
    }

    public Disc() {
        super("Disc", "Disc", "disc");
        this.narrator = "None";
    }

    public Disc(String year, String author, String title, String narrator) {
        super(year, author, title, "disc");
        this.narrator = narrator;
    }

    private Disc(String year, String author, String title, String narrator, int id) {
        super(year, author, title, id, "disc");
        this.narrator = narrator;
    }

    public Disc(String title, String author, String year, String amount, String narrator) {
        super(title, author, year, amount, "disc");
        this.narrator = narrator;
    }

    public String getNarrator() {
        return narrator;
    }

    @Override
    public String toString() {
        return super.toString() + ", " + narrator;
    }

    @Override
    public boolean equals(Publication p) {
        if (p instanceof Disc comp) {
            return super.equals(p) & comp.getNarrator().equals(narrator);
        }
        return false;
    }

    @Override
    public Publication clone() {
        return new Disc(getYear(), getAuthor(), getTitle(), this.narrator, getId());
    }

    public static Publication create(String[] info) {
        return new Disc(info[0], info[1], info[2], info[3], info[4]);
    }

    @Override
    public void update(String[] info) {
        super.update(info);
        String newNarrator = info[4];
        if (!newNarrator.isEmpty()) {
            this.narrator = newNarrator;
        }
    }
}
