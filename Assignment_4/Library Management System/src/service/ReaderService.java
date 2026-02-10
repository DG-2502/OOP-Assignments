package service;

import domain.user.Reader;
import domain.publications.Publication;
import repository.PublicationRepository;
import util.Pair;

import java.util.HashMap;
import java.util.Map;

public class ReaderService extends UserService {
    private final Reader reader;
    private final IssueService issueService;
    private final Map<Integer, Integer> map = new HashMap<>();

    public ReaderService(Reader reader, PublicationRepository publications, IssueService issueService) {
        super(publications);
        this.reader = reader;
        this.issueService = issueService;
    }

    public PublicationRepository getReaderPublications() {
        return reader.getPublications();
    }

    public Pair takePublication() {
        if (reader.hasPublication(getChosenPubId())) {
            return new Pair(false, "You already have a copy of this publication");
        }
        if (getPublications().getByID(getChosenPubId()).decrease()) {
            Publication taken = getPublications().getByID(getChosenPubId()).clone();
            reader.addPublication(taken);
            return new Pair(true, "Please choose for how long you want to take the publication");
        }
        return new Pair(false, "Could not issue a publication, no copy available at the moment!");
    }

    public void makeIssue(int dayTaken, int dayPlannedReturn) {
        Publication taken = reader.getPublications().getByID(getChosenPubId());
        int issueId = issueService.createIssue(dayTaken, dayPlannedReturn, reader, taken);
        map.put(getChosenPubId(), issueId);
        System.out.println(map);
    }

    public void returnPublication(int day) {
        Publication taken = reader.getPublications().getByID(getChosenPubId());
        getPublications().add(taken);
        reader.getPublications().remove(getChosenPubId());
        int issueId = map.get(getChosenPubId());
        issueService.closeById(issueId, day);
        map.remove(getChosenPubId(), issueId);
        System.out.println(map);
    }
}
