package service;

import domain.human.Reader;
import domain.issue.Issue;
import domain.publications.Publication;
import repository.LibraryRepository;

public class ReaderService {
    private final Reader reader;
    private final LibraryRepository library;

    public ReaderService(Reader reader, LibraryRepository library) {
        this.reader = reader;
        this.library = library;
    }

    public boolean takePublication(Publication publication) {
        if (publication == null) {
            System.out.println("Please specify the publication");
            return false;
        }
        if (reader.hasPublication(publication)){
            System.out.println("You already have a copy of this publication");
            return false;
        }
        if (!library.getPublications().hasCopy(publication)){
            System.out.println("Could not issue a copy, not available!");
            return false;
        }
        Publication taken = publication.clone();
        reader.addPublicationNew(taken);
        publication.decrease();
        library.addIssue(new Issue(reader, taken));
        return true;
    }

    public void returnPublication() {
    }
}
