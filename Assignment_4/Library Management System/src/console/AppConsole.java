package console;

import domain.publications.*;
import domain.user.*;
import repository.Repository;
import service.*;
import util.Pair;

import java.util.ArrayList;

public class AppConsole extends BasicConsole {
    private Console userConsole;
    private boolean loginOption = false;
    private int chosenUserID;
    private final UsersService usersService;
    private final PublicationsService publicationsService;
    private final IssuesService issuesService;

    public AppConsole() {
        this.usersService = new UsersService();
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
        Pair pair = usersService.getUser(name);
        System.out.println(pair.value());
        chosenUserID = usersService.getChosenUserID();
        return pair.key();
    }

    private void login() {
        if (userConsole == null) {
            User user = usersService.getUserRepository().getByID(chosenUserID);
            if (user instanceof Reader) {
                this.userConsole = new ReaderConsole(new ReaderService((Reader) user, publicationsService, issuesService));
            } else if (user instanceof Librarian) {
                this.userConsole = new LibrarianConsole(new LibrarianService(publicationsService, usersService, issuesService));
            } else {
                System.out.println("Please implement console and service your new class at first");
            }
            System.out.println("Logged in as: " + user);
        } else {
            System.out.println("Already logged in!");
        }
    }
}
