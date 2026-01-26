package domain.human;

import repository.PublicationRepository;

public class Reader extends Human{
    private final PublicationRepository takenPublications;

    public Reader(String firstName, String lastName, PublicationRepository takenPublications) {
        super(firstName, lastName);
        this.takenPublications = takenPublications;
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
