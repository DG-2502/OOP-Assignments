package console;

import domain.publications.Book;
import domain.publications.Disc;
import domain.publications.Magazine;
import domain.user.Librarian;
import domain.user.Reader;
import domain.user.User;
import repository.PublicationRepository;
import repository.Repository;
import repository.UserRepository;
import service.LibrarianService;
import service.ReaderService;

import java.util.ArrayList;

public class AppConsole extends BasicConsole {
    private Console userConsole;
    private boolean loginOption = false;
    private int chosenUserID;
    private final Repository<User> userRepository;
    //    private Repository<Publication> publicationRepository;
    private final PublicationRepository publicationRepository;

    public AppConsole() {
        this.userRepository = new UserRepository();
        this.publicationRepository = new PublicationRepository();
        publicationRepository.add(new Book("R.J. Darkwater", "A Culinary History of the Moon"));
        publicationRepository.add(new Book("R.J. Darkwater", "A"));
        publicationRepository.add(new Book("R.J. Darkwater", "A"));
        publicationRepository.add(new Book("Marisol Vega", "The City Where All Clocks Are Wrong"));
        publicationRepository.add(new Disc("Dr. Octavia Vance", "On the Taxonomy of Ghosts"));
        publicationRepository.add(new Disc("L.B. Merriweather", "A Compendium of Forgotten Tuesdays"));
        publicationRepository.add(new Magazine("Professor Alistair Finch", "The Last Postal Clerk of Prague"));
        publicationRepository.add(new Magazine("K.J. Thorne", "The Mosaic of Salt and Iron"));
    }

    @Override
    public void run() {
        executeCommands();
        if (userConsole != null) {
            while (!userConsole.getExitOption()) {
                userConsole.run();
            }
            userConsole = null;
            setHelpOption(true);
            executeCommands();
        }
        readInput();
    }

    @Override
    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)) {
            return true;
        }
        if (command.equals("login")) {
            return loginOption = getUser(option);
        }
        System.out.println("Could not find any matching command! Type help to see more!");
        return false;
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
        if (getHelpOption()) {
            System.out.println("login name/index - login in as the specified user");
            System.out.println("exit - close the application");
            setHelpOption(false);
        }
        if (loginOption) {
            login();
            loginOption = false;
        }
    }

    private boolean getUser(String name) {
        try {
            int index = Integer.parseInt(name);
            if (userRepository.hasId(index)) {
                chosenUserID = index;
                System.out.println("Found one user: " + userRepository.getByID(index));
                return true;
            }
            System.out.println("Could not find user with ID: " + index);
            return false;
        } catch (NumberFormatException e) {
            ArrayList<User> users = new ArrayList<>(userRepository.find(name));
            if (users.isEmpty()) {
                System.out.println("Could not find a user with such a name: " + name);
                return false;
            } else if (users.size() != 1) {
                System.out.println("Found several users with such a name: " + name);
                for (User user : users) {
                    System.out.println(user);
                }
                return false;
            }
            chosenUserID = users.getFirst().getId();
            System.out.println("Found one user with such a name: " + users.getFirst());
            return true;
        }
    }

    private void login() {
        if (userConsole == null) {
            System.out.println("Logging in");
            User user = userRepository.getByID(chosenUserID);
            if (user instanceof Reader) {
                this.userConsole = new ReaderConsole(new ReaderService((Reader) user, publicationRepository));
            } else if (user instanceof Librarian) {
//                System.out.println("Not implemented yet");
                this.userConsole = new LibrarianConsole(new LibrarianService(publicationRepository));
            } else {
                System.out.println("No implementation for that class yet");
            }
            System.out.println("Logged in as: " + user);
        } else {
            System.out.println("Already logged in!");
        }
    }
}
