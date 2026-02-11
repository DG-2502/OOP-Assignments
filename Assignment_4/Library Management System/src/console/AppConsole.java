package console;

import domain.publications.Book;
import domain.publications.Disc;
import domain.publications.Magazine;
import domain.user.Librarian;
import domain.user.Reader;
import domain.user.User;
import repository.Repository;
import repository.UserRepository;
import service.IssuesService;
import service.LibrarianService;
import service.PublicationsService;
import service.ReaderService;

import java.util.ArrayList;

public class AppConsole extends BasicConsole {
    private Console userConsole;
    private boolean loginOption = false;
    private int chosenUserID;
    private final Repository<User> userRepository;
    private final PublicationsService publicationsService;
    private final IssuesService issuesService;

    public AppConsole() {
        this.userRepository = new UserRepository();
        this.publicationsService = new PublicationsService();
        this.issuesService = new IssuesService();
        publicationsService.add(new Book("R.J. Darkwater", "A Culinary History of the Moon"));
        publicationsService.add(new Book("R.J. Darkwater", "A"));
        publicationsService.add(new Book("R.J. Darkwater", "A"));
        publicationsService.add(new Book("Marisol Vega", "The City Where All Clocks Are Wrong"));
        publicationsService.add(new Disc("Dr. Octavia Vance", "On the Taxonomy of Ghosts"));
        publicationsService.add(new Disc("L.B. Merriweather", "A Compendium of Forgotten Tuesdays"));
        publicationsService.add(new Magazine("Professor Alistair Finch", "The Last Postal Clerk of Prague"));
        publicationsService.add(new Magazine("K.J. Thorne", "The Mosaic of Salt and Iron"));
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
            User user = userRepository.getByID(chosenUserID);
            if (user instanceof Reader) {
                this.userConsole = new ReaderConsole(new ReaderService((Reader) user, publicationsService, issuesService), new LibrarianService(publicationsService, userRepository, issuesService));
            } else if (user instanceof Librarian) {
                this.userConsole = new LibrarianConsole(new LibrarianService(publicationsService, userRepository, issuesService));
            } else {
                System.out.println("Please implement console and service your new class at first");
            }
            System.out.println("Logged in as: " + user);
        } else {
            System.out.println("Already logged in!");
        }
    }
}
