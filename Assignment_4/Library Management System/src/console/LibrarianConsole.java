package console;

import domain.publications.Publication;
import domain.user.User;
import service.LibrarianService;
import util.Pair;

import java.util.Arrays;
import java.util.stream.Stream;

public class LibrarianConsole extends UserConsole {
    private final LibrarianService librarianService;
    private boolean listOption = false;
    private boolean registerOption = false;
    private boolean deleteOption = false;
    private boolean updateOption = false;

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
        if (command.equals("update")) {
            query = option;
            return updateOption = true;
        }
        System.out.println("Could not find the command, type 'help' to see the list of possible commands");
        return false;
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
        if (getHelpOption()) {
            System.out.println("register reader/librarian/publication - Register something or somebody");
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
            if (!(query.equals("users") | query.equals("publication"))) {
                System.out.println("Could not register a " + query);
                System.out.println("Possible values are: user, publication");
                return;
            }
            String[] info = getInfo(false, query);
            librarianService.register(info, query);
            System.out.println("Successfully registered a new " + query);
        }
        if (updateOption) {
            updateOption = false;
            int id = 0;
            try {
                id = Integer.parseInt(query);
            } catch (NumberFormatException e) {
                System.out.println("Update command only works with indexes!");
                return;
            }
            Pair pair = librarianService.getTypeById(id);
            query = pair.value();
            if (!pair.key()) {
                System.out.println(query);
                return;
            }
            String[] info = getInfo(true, librarianService.getParent(query));
            librarianService.update(info, id);
            System.out.println("Successfully updated a " + query);
        }
        if (deleteOption) {
            Pair response = librarianService.delete(query);
            System.out.println(response.value());
            deleteOption = false;
        }
    }

    private String[] getInfo(boolean update, String query) {
        if (query.equals("user")) {
            return getUserInfo(update);
        } else if (query.equals("publication")) {
            return getPublicationInfo(update);
        }
        return null;
    }

    private String[] getUserInfo(boolean update) {
        int id;
        if (update) {
            id = User.UserType.valueOf(query).ordinal();
        } else {
            System.out.println("Please choose which user you want to register");
            for (User.UserType userType : User.UserType.values()) {
                System.out.println(userType.ordinal() + ": " + userType.name().toUpperCase());
            }
            id = readInt(0, User.UserType.values().length - 1);
        }
        query = User.UserType.values()[id].name();
        System.out.print("Name: ");
        String name = readName(false);
        System.out.print("Last name: ");
        String last = readName(false);
        System.out.print("Age: ");
        String age = String.valueOf(readInt(0, 1 << 7));
        return new String[]{name, last, age};
    }

    private String[] getPublicationInfo(boolean update) {
        int id;
        if (update) {
            id = Publication.PubType.valueOf(query).ordinal();
        } else {
            System.out.println("Please choose which publication you want to register");
            for (Publication.PubType pubType : Publication.PubType.values()) {
                System.out.println(pubType.ordinal() + ": " + pubType.name().toUpperCase());
            }
            id = readInt(0, Publication.PubType.values().length - 1);
        }
        System.out.print("Title: ");
        String title = readName(true);
        System.out.print("Author: ");
        String author = readName(true);
        System.out.print("Year: ");
        String year = String.valueOf(readInt(0, 2026));
        System.out.print("Amount: ");
        String amount = String.valueOf(readInt(0, 1 << 16));
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
        String pages = String.valueOf(readInt(1, 1 << 16));
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
