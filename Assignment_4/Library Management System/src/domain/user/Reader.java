package domain.user;

import domain.publications.Publication;
import repository.PublicationRepository;
import repository.Repository;

public class Reader extends User {
//    private final Repository
    private final PublicationRepository publications;

    public Reader() {
        super();
        this.publications = new PublicationRepository();
    }

    public Reader(String firstName, String lastName, PublicationRepository takenPublications) {
        super(firstName, lastName);
        this.publications = takenPublications;
    }

    public Reader(String firstName, String lastName) {
        super(firstName, lastName);
        this.publications = new PublicationRepository();
    }

    public Reader(String firstName, String lastName, String age) {
        super(firstName, lastName, age);
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
    public PublicationRepository getPublications() {
        return publications;
    }
}
