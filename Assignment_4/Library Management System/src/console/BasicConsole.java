package console;

import java.util.Scanner;

public abstract class BasicConsole implements Console {
    private boolean exitOption = false;
    private boolean helpOption = true;
    private static int day = 0;

    public void readInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine().trim();
            if (request.matches("\\w+\\s+.+")) {
                String[] split = request.splitWithDelimiters("\\s+", 2);
                if (parseCommand(split[0].toLowerCase(), split[2])) {
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

    public boolean parseCommand(String command, String query) {
        if (command.equals("exit")) {
            return exitOption = true;
        }
        if (command.equals("help")) {
            return helpOption = true;
        }
        if (command.equals("nextday")){
            day += 1;
            System.out.println("The next day has come");
        }
        return false;
    }

    public void executeCommands() {
        if (helpOption) {
            System.out.println("Available commands:");
            System.out.println("help - Show available commands");
        }
    }

    @Override
    public void run() {
        executeCommands();
        readInput();
    }

    public boolean getExitOption(){
        return exitOption;
    }

    public boolean getHelpOption() {
        return helpOption;
    }

    public static int getDay() {
        return day;
    }

    public void setHelpOption(boolean helpOption) {
        this.helpOption = helpOption;
    }
}
