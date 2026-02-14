package service;

import domain.user.Reader;
import domain.user.User;
import repository.Repository;
import repository.UserRepository;
import util.Pair;

import java.util.ArrayList;

public class UsersService {
    private final Repository<User> userRepository;
    private int chosenUserID;

    public UsersService() {
        this.userRepository = new UserRepository();
    }

    public int getChosenUserID() {
        return chosenUserID;
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

    public Pair getUser(String option) {
        try {
            int index = Integer.parseInt(option);
            if (userRepository.hasId(index)) {
                chosenUserID = index;
                return new Pair(true, "Found one user: " + userRepository.getByID(index));
            }
            return new Pair(false, "Could not find user with ID: " + index);
        } catch (NumberFormatException e) {
            ArrayList<User> users = new ArrayList<>(userRepository.find(option));
            if (users.isEmpty()) {
                return new Pair(false, "Could not find a user with such a name: " + option);
            } else if (users.size() != 1) {
                for (User user : users) {
                    System.out.println(user);
                }
                return new Pair(false, "Found several users with such a name: " + option);
            }
            chosenUserID = users.getFirst().getId();
            return new Pair(true, "Found one user with such a name: " + users.getFirst());
        }
    }

    public Repository<User> getUserRepository() {
        return userRepository;
    }
}
