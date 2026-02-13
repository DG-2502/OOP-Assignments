package service;

import domain.user.Reader;
import domain.publications.Publication;
import repository.Repository;
import util.Pair;

public class ReaderService extends UserService {
    private final Reader reader;

    public ReaderService(Reader reader, PublicationsService publicationsService, IssuesService issuesService) {
        super(publicationsService, issuesService);
        this.reader = reader;
    }

    public Repository<Publication> getReaderPublications() {
        return reader.getPublications();
    }

    public Pair takePublication() {
        if (reader.hasPublication(getChosenPubId())) {
            return new Pair(false, "You already have a copy of this publication");
        }
        if (publicationsService.decreaseById(getChosenPubId())) {
            Publication taken = publicationsService.cloneById(getChosenPubId());
            reader.addPublication(taken);
            return new Pair(true, "Please choose for how long you want to take the publication");
        }
        return new Pair(false, "Could not issue a publication, no copy available at the moment!");
    }

    public void makeIssue(String dateTaken, String datePlannedReturn) {
        issuesService.createIssue(dateTaken, datePlannedReturn, reader.getId(), getChosenPubId());
    }

    public void returnPublication(String date) {
        Publication taken = reader.getPublications().getByID(getChosenPubId());
        publicationsService.add(taken);
        reader.getPublications().remove(getChosenPubId());
        issuesService.close(date, reader.getId(), getChosenPubId());
    }
}
