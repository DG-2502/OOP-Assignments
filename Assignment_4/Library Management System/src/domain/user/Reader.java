package domain.user;

import domain.publications.Publication;
import repository.PublicationRepository;
import repository.Repository;

public class Reader extends User {
    private final Repository<Publication> publications;

    public Reader() {
        super("reader");
        this.publications = new PublicationRepository();
    }

    public Reader(String firstName, String lastName, Repository<Publication> takenPublications) {
        super(firstName, lastName, "reader");
        this.publications = takenPublications;
    }

    public Reader(String firstName, String lastName) {
        super(firstName, lastName, "reader");
        this.publications = new PublicationRepository();
    }

    public Reader(String firstName, String lastName, String age) {
        super(firstName, lastName, age, "reader");
        this.publications = new PublicationRepository();
    }

    @Override
    public String toString() {
        return super.toString() + " (Reader)";
    }

    public boolean hasPublication(int id) {
        return publications.hasId(id);
    }

    public void addPublication(Publication publication) {
        publications.add(publication);
    }

    public Repository<Publication> getPublications() {
        return publications;
    }

    public static User create(String[] info) {
        return new Reader(info[0], info[1], info[2]);
    }

    @Override
    public void update(String[] info) {
        super.update(info);
    }
}
