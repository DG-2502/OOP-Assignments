package domain.publications;

public abstract class Publication {
    private final int year;
    private final String author;
    private final String title;
    private int amount;

    public Publication() {
        this.year = 0;
        this.author = "None";
        this.title = "None";
        this.amount = 1;
    }

    public Publication(String author, String title){
        this.year = 0;
        this.author = "None" + author;
        this.title = "None" + title;
    }

    public Publication(int year, String author, String title) {
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

    public int getAmount() {
        return amount;
    }

    @Override
    public String toString() {
        return title + " " + author + " " + year + " " + amount;
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
