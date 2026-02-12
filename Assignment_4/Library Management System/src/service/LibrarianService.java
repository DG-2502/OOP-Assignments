package service;

import domain.issue.Issue;
import domain.publications.Book;
import domain.publications.Disc;
import domain.publications.Magazine;
import domain.user.Librarian;
import domain.user.Reader;
import domain.user.User;
import repository.Repository;
import util.Pair;

public class LibrarianService extends UserService {
    private final UsersService usersService;

    public LibrarianService(PublicationsService publicationsService, UsersService usersService, IssuesService issuesService) {
        super(publicationsService, issuesService);
        this.usersService = usersService;
    }

    public Repository<User> getUserRepository() {
        return usersService.getUserRepository();
    }

    public Repository<Issue> getIssueRepository() {
        return issuesService.getIssueRepository();
    }

    public boolean register(String[] info, String option) {
        if (option.equals("reader")) {
            usersService.add(new Reader(info[0], info[1], info[2]));
            return true;
        } else if (option.equals("librarian")) {
            usersService.add(new Librarian(info[0], info[1], info[2]));
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

    public Pair delete(String option) {
        try {
            int id = Integer.parseInt(option);
            Pair pair = usersService.removeById(id);
            if (pair.key()) {
                return pair;
            }
            if (publicationsService.removeById(id)) {
                return new Pair(true, "Successfully removed a publication with ID: " + option);
            }
            pair = issuesService.removeById(id);
            if (pair.key()) {
                return pair;
            }
            return new Pair(false, "Could not find any entity with this ID: " + id);
        } catch (NumberFormatException e) {
            return new Pair(false, "Deleting by name is not implemented yet");
        }
    }

    public Librarian getLib() {
        return usersService.getLibrarian();
    }
}
