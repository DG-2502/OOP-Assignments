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

//    @Override
//    public boolean has(Issue issue) {
//        return issues.contains(issue);
//    }

    @Override
    public boolean hasId(int id) {
        return false;
    }

    @Override
    public void print() {
        for (Issue issue : issues) {
            System.out.println(issue);
        }
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
