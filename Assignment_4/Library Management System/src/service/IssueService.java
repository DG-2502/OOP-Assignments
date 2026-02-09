package service;

import domain.issue.Issue;
import domain.publications.Publication;
import domain.user.Reader;
import repository.IssueRepository;
import repository.Repository;

import java.util.Scanner;

public class IssueService {
    private final Repository<Issue> issueRepository;

    public IssueService() {
        this.issueRepository = new IssueRepository();
    }

    public Issue createIssue(int day, Reader reader, Publication publication) {
        Scanner scanner = new Scanner(System.in);
        String nextline;
        while (true) {
            nextline = scanner.nextLine();
            int number = 0;
            try {
                number = Integer.parseInt(nextline);
            } catch (NumberFormatException e) {
                System.out.println("Please type a number");
                continue;
            }
            if (number >= 1 & number <= 28) {
                Issue issue = new Issue(day, reader, publication);
                issueRepository.add(issue);
                return issue;
            }
            System.out.println("Please type a number between 1 and 28");
        }
    }
}
