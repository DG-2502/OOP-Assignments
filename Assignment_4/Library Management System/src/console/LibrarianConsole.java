package console;

import service.LibrarianService;
import util.Pair;
import java.util.Arrays;
import java.util.stream.Stream;

public class LibrarianConsole extends UserConsole {
    private final LibrarianService librarianService;
    private boolean listOption = false;
    private boolean registerOption = false;
    private boolean deleteOption = false;

    public LibrarianConsole(LibrarianService librarianService) {
        this.librarianService = librarianService;
    }

    @Override
    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)) {
            return true;
        }
        if (command.equals("register")) {
            query = option.toLowerCase();
            return registerOption = true;
        }
        if (command.equals("list")) {
            query = option.toLowerCase();
            return listOption = true;
        }
        if (command.equals("delete")) {
            query = option.toLowerCase();
            return deleteOption = true;
        }
        System.out.println("Could not find the command, type 'help' to see the list of possible commands");
        return false;
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
        if (getHelpOption()) {
            System.out.println("register reader/librarian/publication - Register something ore somebody");
            System.out.println("list - show users, publications, issues of the library");
            System.out.println("delete index/query - delete something or someone from the library");
            setHelpOption(false);
        }
        if (listOption) {
            if (query.equals("users")) {
                System.out.println("**Users**");
                librarianService.getUserRepository().getAll().forEach(System.out::println);
            } else if (query.equals("publications")) {
                System.out.println("**Publications**");
                librarianService.getPublicationRepository().getAll().forEach(System.out::println);
            } else if (query.equals("issues")) {
                System.out.println("**Issues**");
                librarianService.getIssueRepository().getAll().forEach(System.out::println);
            } else {
                System.out.println("Could not list: " + query);
                System.out.println("Possible values are: users, publications, issues");
            }
            listOption = false;
        }
        if (registerOption) {
            registerOption = false;
            String[] info = null;
            if (query.equals("reader") | query.equals("librarian")) {
                info = getUserInfo();
            }
            else if (query.equals("publication")) {
                info = getPublicationInfo();
            }
            if (librarianService.register(info, query)) {
                System.out.println("Successfully registered a new " + query);
                return;
            }
            System.out.println("Could not register a " + query);
            System.out.println("Possible values are: reader, librarian, publication");
        }
        if (deleteOption) {
            Pair response = librarianService.delete(query);
            System.out.println(response.value());
            deleteOption = false;
        }
    }

    private String[] getUserInfo() {
        System.out.print("Name: ");
        String name = readName(false);
        System.out.print("Last name: ");
        String last = readName(false);
        System.out.print("Age: ");
        String age = String.valueOf(readInt(0, 1<<7));
        return new String[]{name, last, age};
    }

    private String[] getPublicationInfo() {
        System.out.println("Please choose which publication you want to register\n0: Book\n1: Disc\n2: Magazine");
        int id = readInt(0, 2);
        System.out.print("Title: ");
        String title = readName(true);
        System.out.print("Author: ");
        String author = readName(true);
        System.out.print("Year: ");
        String year  = String.valueOf(readInt(0, 2026));
        System.out.print("Amount: ");
        String amount = String.valueOf(readInt(0, 1<<16));
        String[] info = {title, author, year, amount};
        String[] result = new String[0];
        result = switch (id) {
            case 0 -> Stream.concat(Arrays.stream(info), Arrays.stream(getBookInfo())).toArray(String[]::new);
            case 1 -> Stream.concat(Arrays.stream(info), Arrays.stream(getDiscInfo())).toArray(String[]::new);
            case 2 -> Stream.concat(Arrays.stream(info), Arrays.stream(getMagazineInfo())).toArray(String[]::new);
            default -> result;
        };
        return result;
    }
    private String[] getBookInfo() {
        query = "book";
        System.out.print("Pages: ");
        String pages = String.valueOf(readInt(1, 1<<16));
        return new String[]{pages};
    }
    private String[] getDiscInfo() {
        query = "disc";
        System.out.print("Narrator: ");
        String narrator = readName(true);
        return new String[]{narrator};
    }
    private String[] getMagazineInfo() {
        query = "magazine";
        return new String[]{};
    }
}
