package service;

import domain.issue.Issue;
import domain.user.Librarian;
import domain.user.Reader;
import domain.user.User;
import repository.PublicationRepository;
import repository.Repository;

import java.util.Scanner;

public class LibrarianService extends UserService {
    private int chosenUserID;
    private final Repository<User> userRepository;

    public LibrarianService(PublicationRepository publications, Repository<User> userRepository, IssueService issueService) {
        super(publications, issueService);
        this.userRepository = userRepository;
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }

    public Repository<Issue> getIssueRepository() {
        return issueService.getIssueRepository();
    }

    private String[] registerUser() {
        Scanner scanner = new Scanner(System.in);
        String line;
        String name;
        String last;
        System.out.println("Name: ");
        while (true) {
            line = scanner.nextLine();
            if (line.matches("[a-zA-Z]+")) {
                name = line;
                break;
            }
            System.out.println("Please enter the name correctly!");
        }
        System.out.println("Last name: ");
        while (true) {
            line = scanner.nextLine();
            if (line.matches("[a-zA-Z]+")) {
                last = line;
                break;
            }
            System.out.println("Please enter the last name correctly!");
        }
        return (name + " " + last).split(" ");
    }

    private void registerReader() {
        String[] name = registerUser();
        userRepository.add(new Reader(name[0], name[1]));
    }

    private void registerLibrarian() {
        String[] name = registerUser();
        userRepository.add(new Librarian(name[0], name[1]));
    }

    public boolean register(String option) {
        if (option.equals("reader")) {
            registerReader();
            return true;
        }
        if (option.equals("librarian")) {
            registerLibrarian();
            return true;
        }
        System.out.println("Could not register: " + option);
        System.out.println("You can register: reader, librarian!");
        return false;
    }

    public boolean delete(String option) {
        try {
            int id = Integer.parseInt(option);
            if (userRepository.hasId(id)) {
                userRepository.remove(id);
                return true;
            }
            if (publications.hasId(id)) {
                publications.remove(id);
                return true;
            }
            System.out.println("Could not find any entity with this ID: " + id);
        } catch (NumberFormatException e) {
            System.out.println("Deleting by name is not implemented yet");
        }
        return false;
    }
}
