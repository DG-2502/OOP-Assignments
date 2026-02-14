package service;

import domain.Entity;
import domain.issue.Issue;
import domain.publications.Publication;
import domain.user.User;
import repository.Repository;
import util.Pair;
import java.util.Arrays;

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
        if (Arrays.stream(User.UserType.values()).anyMatch(type -> type.name().equals(option))) {
            usersService.add(User.createByType(User.UserType.valueOf(option), info));
            return true;
        } else if (Arrays.stream(Publication.PubType.values()).anyMatch(pubType -> pubType.name().equals(option))) {
            publicationsService.add(Publication.createByType(Publication.PubType.valueOf(option), info));
            return true;
        }
        return false;
    }

    public void update(String[] info, int id) {
        getByID(id).update(info);
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

    public Entity getByID(int id) {
        if (getUserRepository().hasId(id)) {
            return getUserRepository().getByID(id);
        }
        if (getPublicationRepository().hasId(id)) {
            return getPublicationRepository().getByID(id);
        }
        if (getIssueRepository().hasId(id)) {
            return getIssueRepository().getByID(id);
        }
        return null;
    }

    public Pair getTypeById(int id) {
        Entity entity = getByID(id);
        if (entity == null) {
            return new Pair(false, "Could not find anything with such an ID: " + id);
        }
        String type = entity.getType();
        if (Arrays.stream(User.UserType.values()).anyMatch(type1 -> type1.name().equals(type))) {
            return new Pair(true, type);
        } else if (Arrays.stream(Publication.PubType.values()).anyMatch(type1 -> type1.name().equals(type))) {
            return new Pair(true, type);
        }
        return new Pair(false, "Can only update users and publications!");
    }

    public String getParent(String type) {
        if (Arrays.stream(User.UserType.values()).anyMatch(type1 -> type1.name().equals(type))) {
            return "user";
        } else if (Arrays.stream(Publication.PubType.values()).anyMatch(type1 -> type1.name().equals(type))) {
            return "publication";
        }
        return null;
    }
}
