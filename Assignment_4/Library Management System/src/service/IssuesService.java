package service;

import domain.issue.Issue;
import repository.IssueRepository;
import repository.Repository;

public class IssuesService {
    private final Repository<Issue> issueRepository;

    public IssuesService() {
        this.issueRepository = new IssueRepository();
    }

    public void createIssue(String dateTaken, String datePlannedReturn, int readerId, int publicationId) {
        Issue issue = new Issue(dateTaken, datePlannedReturn, readerId, publicationId);
        issueRepository.add(issue);
    }

    public void close(String dateReturned, int readerId, int pubId) {
        issueRepository.getAll().stream().filter(issue -> issue.getReaderId() == readerId & issue.getPubId() == pubId).findFirst().get().close(dateReturned);
    }

    public Repository<Issue> getIssueRepository() {
        return issueRepository;
    }
}
