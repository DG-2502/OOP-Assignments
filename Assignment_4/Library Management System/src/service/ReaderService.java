package service;

import domain.user.Reader;
import domain.publications.Publication;
import repository.PublicationRepository;

public class ReaderService extends UserService {
    private final Reader reader;

    public ReaderService(Reader reader, PublicationRepository publications) {
        super(publications);
        this.reader = reader;
    }

    public PublicationRepository getReaderPublications() {
        return reader.getPublications();
    }

    public boolean takePublication() {
        if (reader.hasPublication(getChosenPubId())) {
            System.out.println("You already have a copy of this publication");
            return false;
        }
        if (getPublications().getByID(getChosenPubId()).decrease()) {
            Publication taken = getPublications().getByID(getChosenPubId()).clone();
            reader.addPublication(taken);
//            library.addIssue(new Issue(reader, taken));
            System.out.println("Should make an Issue");
            return true;
        }
        System.out.println("Could not issue a publication, no copy available at the moment!");
        return false;
    }

    public void returnPublication() {
//        publications.addByID(getChosenPubId());
        getPublications().add(getPublications().getByID(getChosenPubId()));
        reader.getPublications().remove(getChosenPubId());
        System.out.println("Should close Issue");
    }
}
