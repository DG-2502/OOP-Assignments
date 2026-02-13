package repository;

import domain.publications.Publication;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class PublicationRepository implements Repository<Publication> {
    private final ArrayList<Publication> publications = new ArrayList<>() {
    };

    @Override
    public void add(Publication publication) {
        publications.add(publication);
    }

    @Override
    public void remove(int id) {
        publications.remove(getByID(id));
    }

    @Override
    public boolean hasId(int id) {
        return publications.stream().anyMatch(publication -> publication.getId() == id);
    }

    @Override
    public Publication getByID(int id) {
        return publications.stream().filter(publication -> publication.getId() == id).findFirst().get();
    }

    @Override
    public ArrayList<Publication> getAll() {
        return publications;
    }

    @Override
    public ArrayList<Publication> find(String title) {
        return (ArrayList<Publication>) publications.stream().filter(publication -> publication.getTitle().equals(title)).collect(Collectors.toList());
    }
}
