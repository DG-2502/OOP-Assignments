package service;

import repository.PublicationRepository;

public class LibrarianService extends UserService {
    private int chosenUserID;

    public LibrarianService(PublicationRepository publications) {
        super(publications);
    }
}
