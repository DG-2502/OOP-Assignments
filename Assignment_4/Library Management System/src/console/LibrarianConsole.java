package console;

import service.LibrarianService;

public class LibrarianConsole extends UserConsole {
    private final LibrarianService librarianService;

    public LibrarianConsole(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @Override
    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)){
            return true;
        }
        System.out.println("Could not find the command, type 'help' to see the list of possible commands");
        return false;
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
        if (getHelpOption()) {
            setHelpOption(false);
        }
    }
}
