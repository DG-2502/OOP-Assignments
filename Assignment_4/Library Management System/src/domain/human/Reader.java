package domain.human;

import domain.publications.Publication;
import repository.PublicationRepository;

public class Reader extends Human{

    private final PublicationRepository publicationRepository;

    public Reader() {
        super();
        this.publicationRepository = new PublicationRepository();
    }

    public Reader(String firstName, String lastName, PublicationRepository takenPublications) {
        super(firstName, lastName);
        this.publicationRepository = takenPublications;
    }

    public Reader(String firstName, String lastName){
        super(firstName, lastName);;
        this.publicationRepository = new PublicationRepository();
    }

    @Override
    public String toString() {
        return super.toString() + "\n    Has these publications:\n" + publicationRepository.toString();
    }

    public boolean hasPublication(Publication publication){
        return publicationRepository.has(publication);
    }

    public void addPublication(Publication publication){
        publicationRepository.add(publication);
    }
    public void returnPublication(Publication publication){
        publicationRepository.remove(publication);
    }

}
