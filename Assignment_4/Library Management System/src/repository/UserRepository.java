package repository;

import domain.user.Librarian;
import domain.user.Reader;
import domain.user.User;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class UserRepository implements Repository<User> {
    private final ArrayList<User> users = new ArrayList<User>(){{
        add(new Reader("Marley", "Buck"));
        add(new Reader("Carley", "Robinson"));
        add(new Reader("Carley", "Robinson"));
        add(new Reader("Joshua", "Estes"));
        add(new Librarian("Rosemary", "Mendez"));
        add(new Librarian("Lindsey", "Stanley"));
        add(new Librarian("Carley", "Robinson"));
        add(new Librarian("Gustavo", "Vaughan"));
    }};

    @Override
    public void add(User user) {
        users.add(user);
    }

    @Override
    public void remove(int id) {
        users.remove(getByID(id));
    }

//    @Override
//    public boolean has(User user) {
//        return users.contains(user);
//    }

    @Override
    public boolean hasId(int id) {
        return users.stream().anyMatch(user -> user.getId() == id);
    }

    @Override
    public void print() {
        for (User user : users){
            System.out.println(user);
        }
    }

    @Override
    public User getByID(int id) {
        return users.stream().filter(user -> user.getId() == id).findFirst().get();
    }

    @Override
    public ArrayList<User> find(String name) {
        return (ArrayList<User>) users.stream().filter(user -> user.getFirstName().equalsIgnoreCase(name) || user.getLastName().equalsIgnoreCase(name)).collect(Collectors.toList());

    }
}
