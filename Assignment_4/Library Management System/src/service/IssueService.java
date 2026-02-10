package service;

import domain.issue.Issue;
import domain.publications.Publication;
import domain.user.Reader;
import repository.IssueRepository;
import repository.Repository;

public class IssueService {
    private final Repository<Issue> issueRepository;

    public IssueService() {
        this.issueRepository = new IssueRepository();
    }

    public int createIssue(int dayTaken, int dayPlannedReturn, Reader reader, Publication publication) {
        Issue issue = new Issue(dayTaken, dayPlannedReturn, reader, publication);
        issueRepository.add(issue);
        return issue.getId();
    }

    public void closeById(int id, int day) {
        issueRepository.getByID(id).close(day);
    }
}
