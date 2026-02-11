package service;

import domain.publications.Publication;
import repository.PublicationRepository;
import repository.Repository;

public class PublicationsService {
    private final Repository<Publication> publicationRepository;

    public PublicationsService() {
        this.publicationRepository = new PublicationRepository();
    }

    public void add(Publication publication) {
        if (publicationRepository.hasId(publication.getId())) {
            publicationRepository.getByID(publication.getId()).increase();
        } else {
            publicationRepository.add(publication);
        }
    }

    public boolean decreaseById(int id) {
        return publicationRepository.getByID(id).decrease();
    }

    public Publication cloneById(int id) {
        return publicationRepository.getByID(id).clone();
    }

    public boolean removeById(int id) {
        if (publicationRepository.hasId(id)) {
            publicationRepository.remove(id);
            return true;
        }
        return false;
    }

    public Repository<Publication> getPublicationRepository() {
        return publicationRepository;
    }
}
