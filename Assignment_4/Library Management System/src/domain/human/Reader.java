package domain.human;

import domain.publications.Publication;
import repository.PublicationRepository;

public class Reader extends Human{
    private final PublicationRepository publications;

    public Reader() {
        super();
        this.publications = new PublicationRepository();
    }

    public Reader(String firstName, String lastName, PublicationRepository takenPublications) {
        super(firstName, lastName);
        this.publications = takenPublications;
    }

    public Reader(String firstName, String lastName){
        super(firstName, lastName);;
        this.publications = new PublicationRepository();
    }

    @Override
    public String toString() {
        return super.toString() + "\n    Has these publications:\n" + publications.toString();
    }

    public boolean hasPublication(Publication publication) {
        return publications.has(publication);
    }

    public void addPublication(Publication publication){
        publications.add(publication);
    }
    public void addPublicationNew(Publication publication) {
        publications.addNew(publication);
    }
    public PublicationRepository getPublications() {
        return publications;
    }
}
