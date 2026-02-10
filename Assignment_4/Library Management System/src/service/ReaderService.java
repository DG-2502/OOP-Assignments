package service;

import domain.user.Reader;
import domain.publications.Publication;
import repository.PublicationRepository;
import util.Pair;

public class ReaderService extends UserService {
    private final Reader reader;

    public ReaderService(Reader reader, PublicationRepository publications, IssueService issueService) {
        super(publications, issueService);
        this.reader = reader;
    }

    public PublicationRepository getReaderPublications() {
        return reader.getPublications();
    }

    public Pair takePublication() {
        if (reader.hasPublication(getChosenPubId())) {
            return new Pair(false, "You already have a copy of this publication");
        }
        if (publications.getByID(getChosenPubId()).decrease()) {
            Publication taken = publications.getByID(getChosenPubId()).clone();
            reader.addPublication(taken);
            return new Pair(true, "Please choose for how long you want to take the publication");
        }
        return new Pair(false, "Could not issue a publication, no copy available at the moment!");
    }

    public void makeIssue(int dayTaken, int dayPlannedReturn) {
        issueService.createIssue(dayTaken, dayPlannedReturn, reader.getId(), getChosenPubId());
    }

    public void returnPublication(int day) {
        Publication taken = reader.getPublications().getByID(getChosenPubId());
        publications.add(taken);
        reader.getPublications().remove(getChosenPubId());
        issueService.close(day, reader.getId(), getChosenPubId());
    }
}
