package domain.library;

import repository.publications.*;

public class Library {
    private final BookRepository bookRepository;
    private final MagazineRepository magazineRepository;
    private final DiscRepository discRepository;


    public Library() {
        this.bookRepository = new BookRepository();
        this.magazineRepository = new MagazineRepository();
        this.discRepository = new DiscRepository();
    }


    public Library(BookRepository bookRepository, MagazineRepository magazineRepository, DiscRepository discRepository) {
        this.bookRepository = bookRepository;
        this.magazineRepository = magazineRepository;
        this.discRepository = discRepository;
    }
}
