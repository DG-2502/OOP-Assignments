package repository.library;

import domain.publications.*;
import repository.publications.*;

public class Library {
    private final PublicationRepository<Book> bookRepository;
    private final PublicationRepository<Magazine> magazineRepository;
    private final PublicationRepository<Disc> discRepository;


    public Library() {
        this.bookRepository = new PublicationRepository<>();
        this.magazineRepository = new PublicationRepository<>();
        this.discRepository = new PublicationRepository<>();
    }


    public Library(PublicationRepository<Book> bookRepository, PublicationRepository<Magazine> magazineRepository, PublicationRepository<Disc> discRepository) {
        this.bookRepository = bookRepository;
        this.magazineRepository = magazineRepository;
        this.discRepository = discRepository;
    }
}
