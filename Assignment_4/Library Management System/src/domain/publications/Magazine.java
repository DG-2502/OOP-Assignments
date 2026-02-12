package domain.publications;

public class Magazine extends Publication{
    public Magazine(String author, String title) {
        super(author, title, "magazine");
    }
    public Magazine() {
        super("Magazine", "Magazine", "magazine");
    }
    public Magazine(int year, String author, String title) {
        super(year, author, title, "magazine");
    }
    private Magazine(int year, String author, String title, int id) {
        super(year, author, title, id, "magazine");
    }

    public Magazine(String title, String author, String year, String amount) {
        super(title, author, year, amount, "magazine");
    }

//    @Override
//    public boolean compare(Publication p) {
//        if (p instanceof Magazine comp) {
//            return super.compare(p);
//        }
//        return super.compare(p);
//    }

    @Override
    public Publication clone() {
        return new Magazine(getYear(), getAuthor(), getTitle(), getId());
    }

    public static Publication create(String[] info) {
        return new Magazine(info[0], info[1], info[2], info[3]);
    }
}
