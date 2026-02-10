package service;

import domain.issue.Issue;
import repository.IssueRepository;
import repository.Repository;

public class IssueService {
    private final Repository<Issue> issueRepository;

    public IssueService() {
        this.issueRepository = new IssueRepository();
    }

    public void createIssue(int dayTaken, int dayPlannedReturn, int readerId, int publicationId) {
        Issue issue = new Issue(dayTaken, dayPlannedReturn, readerId, publicationId);
        issueRepository.add(issue);
    }

    public void close(int day, int readerId, int pubId) {
        issueRepository.getAll().stream().filter(issue -> issue.getReaderId() == readerId & issue.getPubId() == pubId).findFirst().get().close(day);
    }

    public Repository<Issue> getIssueRepository() {
        return issueRepository;
    }
}
