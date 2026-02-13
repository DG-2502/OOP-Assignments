package service;

import domain.publications.Publication;
import repository.PublicationRepository;
import repository.Repository;

public class PublicationsService {
    private final Repository<Publication> publicationRepository;

    public PublicationsService() {
        this.publicationRepository = new PublicationRepository();
    }

    public void add(Publication pub) {
        if (publicationRepository.getAll().stream().anyMatch(p -> p.equals(pub))){
            int id = publicationRepository.getAll().stream().filter(p -> p.equals(pub)).findFirst().get().getId();
            publicationRepository.getByID(id).increase(pub.getAmount());
        }
        else if (publicationRepository.hasId(pub.getId())) {
            publicationRepository.getByID(pub.getId()).increase(1);
        } else {
            publicationRepository.add(pub);
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
