package service;

import domain.user.Librarian;
import domain.user.Reader;
import domain.user.User;
import repository.Repository;
import repository.UserRepository;
import util.Pair;

public class UsersService {
    private final Repository<User> userRepository;


    public UsersService() {
        this.userRepository = new UserRepository();
    }

    public void add(User user) {
        userRepository.add(user);
    }

    public Pair removeById(int id) {
        if (!userRepository.hasId(id)) {
            return new Pair(false, "");
        }
        if (userRepository.getByID(id) instanceof Reader reader) {
            if (reader.getPublications().getAll().isEmpty()) {
                userRepository.remove(id);
                return new Pair(true, "Successfully removed a reader with ID: " + id);
            }
            return new Pair(true, "Could not remove a reader with ID: " + id + ", as he still has unreturned publications!");
        }
        userRepository.remove(id);
        return new Pair(true, "Successfully removed a librarian with ID: " + id);
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }

    public Librarian getLibrarian() {
        return ((Librarian) userRepository.getAll().stream().filter(user -> user instanceof Librarian).findAny().get());
    }
}
