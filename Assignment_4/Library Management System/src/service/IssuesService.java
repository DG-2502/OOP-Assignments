package service;

import domain.issue.Issue;
import repository.IssueRepository;
import repository.Repository;
import util.Pair;

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

    public Pair removeById(int id) {
        if (!issueRepository.hasId(id)) {
            return new Pair(false, "");
        }
        if (issueRepository.getByID(id).isClosed()) {
            issueRepository.remove(id);
            return new Pair(true, "Successfully removed an issue with ID: " + id);
        }
        return new Pair(true, "Could not remove an issue with ID: " + id + ", as it is still open!");
    }

    public Repository<Issue> getIssueRepository() {
        return issueRepository;
    }
}
