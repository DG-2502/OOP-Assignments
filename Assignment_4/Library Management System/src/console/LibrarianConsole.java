package console;

import service.LibrarianService;

public class LibrarianConsole extends UserConsole {
    private final LibrarianService librarianService;
    private boolean listOption = false;

    public LibrarianConsole(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @Override
    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)){
            return true;
        }
        if (command.equals("register")) {
            return librarianService.register(option.toLowerCase());
        }
        if (command.equals("list")) {
            return listOption = true;
        }
        if (command.equals("delete")) {
            return  librarianService.delete(option);
        }
        System.out.println("Could not find the command, type 'help' to see the list of possible commands");
        return false;
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
        if (getHelpOption()) {
            System.out.println("register reader/librarian - Register something ore somebody");
            System.out.println("list - show users of the library");
            System.out.println("delete index/query - delete something or someone from the library");
            setHelpOption(false);
        }
        if (listOption) {
            librarianService.listUsers();
        }
    }
}
