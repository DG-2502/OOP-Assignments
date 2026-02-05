package service;

import domain.publications.Publication;

import java.util.Scanner;

public abstract class UserService extends Service implements UserServiceFactory {
    private Publication chosenPub;
    private boolean shouldExit = false;

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine().trim();
            if (request.matches("\\w+\\s+\\w+")) {
                String[] split = request.split("\\s+");
                if (parseCommand(split[0].toLowerCase(), split[1])) {
                    break;
                }
            } else if (request.matches("\\w+")) {
                if (parseCommand(request.toLowerCase(), "none")) {
                    break;
                }
            } else {
                System.out.println("Could not parse the command, please provide the following format: command option/none. Or type 'help'");
            }
        }
    }

    public boolean parseCommand(String command, String option) {
        if (command.equals("exit")) {
            return shouldExit = true;
        }
        return false;
    }

    public void executeCommands(){
        if (shouldExit) {
            System.out.println("Logging out of the system");
        }
    }

    public Publication getChosenPub() {
        return chosenPub;
    }

    public void setChosenPub(Publication chosenPub) {
        this.chosenPub = chosenPub;
    }

    public boolean getShouldExit() {
        return shouldExit;
    }
}
