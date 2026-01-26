package repository;

import domain.issue.Issue;

import java.util.ArrayList;

public class IssueRepository implements Repository<Issue> {
    private final ArrayList<Issue> issues = new ArrayList<>();

    @Override
    public void add(Issue issue) {
        issues.add(issue);
    }

    @Override
    public void remove(int i) {
        issues.remove(i);
    }

    public Issue getById(int i){
        return issues.get(i);
    }
}
