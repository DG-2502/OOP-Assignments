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
    public void remove(int id) {
        issues.remove(getByID(id));
    }

    @Override
    public boolean hasId(int id) {
        return issues.stream().anyMatch(issue -> issue.getId() == id);
    }

    @Override
    public ArrayList<Issue> getAll() {
        return issues;
    }

    @Override
    public Issue getByID(int id) {
        return issues.stream().filter(issue -> issue.getId() == id).findFirst().get();
    }

    @Override
    public ArrayList<Issue> find(String string) {
        return null;
    }
}
