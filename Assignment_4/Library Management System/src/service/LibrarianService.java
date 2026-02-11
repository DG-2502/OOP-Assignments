package service;

import domain.issue.Issue;
import domain.publications.Book;
import domain.publications.Disc;
import domain.publications.Magazine;
import domain.user.Librarian;
import domain.user.Reader;
import domain.user.User;
import repository.Repository;

public class LibrarianService extends UserService {
    private final Repository<User> userRepository;

    public LibrarianService(PublicationsService publicationsService, Repository<User> userRepository, IssuesService issuesService) {
        super(publicationsService, issuesService);
        this.userRepository = userRepository;
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }

    public Repository<Issue> getIssueRepository() {
        return issuesService.getIssueRepository();
    }

    public boolean register(String[] info, String option) {
        if (option.equals("reader")) {
            userRepository.add(new Reader(info[0], info[1], info[2]));
            return true;
        } else if (option.equals("librarian")) {
            userRepository.add(new Librarian(info[0], info[1], info[2]));
            return true;
        } else if (option.equals("book")) {
            publicationsService.add(new Book(info[0], info[1], info[2], info[3], info[4]));
            return true;
        } else if (option.equals("disc")) {
            publicationsService.add(new Disc(info[0], info[1], info[2], info[3], info[4]));
            return true;
        } else if (option.equals("magazine")) {
            publicationsService.add(new Magazine(info[0], info[1], info[2], info[3]));
            return true;
        }
        return false;
    }

    public boolean delete(String option) {
        try {
            int id = Integer.parseInt(option);
            if (userRepository.hasId(id)) {
                userRepository.remove(id);
                return true;
            }
            if (publicationsService.removeById(id)) {
                return true;
            }
            System.out.println("Could not find any entity with this ID: " + id);
        } catch (NumberFormatException e) {
            System.out.println("Deleting by name is not implemented yet");
        }
        return false;
    }
}
