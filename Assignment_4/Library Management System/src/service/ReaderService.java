package service;

import domain.issue.Issue;
import domain.user.Reader;
import domain.publications.Publication;
import repository.PublicationRepository;

import java.util.HashMap;
import java.util.Map;

public class ReaderService extends UserService {
    private final Reader reader;
    private final IssueService issueService;
    private final Map<Publication, Issue> publicationMap = new HashMap<>();

    public ReaderService(Reader reader, PublicationRepository publications, IssueService issueService) {
        super(publications);
        this.reader = reader;
        this.issueService = issueService;
    }

    public PublicationRepository getReaderPublications() {
        return reader.getPublications();
    }

    public boolean takePublication(int day) {
        if (reader.hasPublication(getChosenPubId())) {
            System.out.println("You already have a copy of this publication");
            return false;
        }
        if (getPublications().getByID(getChosenPubId()).decrease()) {
            Publication taken = getPublications().getByID(getChosenPubId()).clone();
            reader.addPublication(taken);
//            library.addIssue(new Issue(reader, taken));
            System.out.println("Please choose for how long you want to take the publication");
            Issue issue = issueService.createIssue(day, reader, taken);
            publicationMap.put(taken, issue);
            System.out.println(publicationMap);
            return true;
        }
        System.out.println("Could not issue a publication, no copy available at the moment!");
        return false;
    }

    public void returnPublication(int day) {
//        publications.addByID(getChosenPubId());
        Publication taken = reader.getPublications().getByID(getChosenPubId());
        getPublications().add(taken);
        reader.getPublications().remove(getChosenPubId());
        System.out.println("Should close Issue");
        Issue issue = publicationMap.get(taken);
        issue.close(day);
        publicationMap.remove(taken, issue);
        System.out.println(publicationMap);
    }
}
