package console;

import service.LibrarianService;
import service.ReaderService;
import util.Pair;

public class ReaderConsole extends UserConsole {
    private final ReaderService readerService;
//    private final LibrarianService librarianService;
    private boolean showOption = false;
    private boolean listOption = false;
    private boolean takeOption = false;
    private boolean returnOption = false;
    private boolean nextDayOption = false;
    private static int day = 0;

    public ReaderConsole(ReaderService readerService, LibrarianService librarianService) {
        this.readerService = readerService;
//        this.librarianService = librarianService;
    }

    @Override
    public boolean parseCommand(String command, String query) {
        this.query = query;
        if (super.parseCommand(command, query)) {
            return true;
        }
        if (command.equals("show")) {
            return showOption = true;
        }
        if (command.equals("list")) {
            return listOption = true;
        }
        if (command.equals("take")) {
            return takeOption = true;
        }
        if (command.equals("return")) {
            return returnOption = true;
        }
        if (command.equals("nextday")) {
            return nextDayOption = true;
        }
        System.out.println("Could not find the command, type 'help' to see the list of possible commands");
        return false;
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
        if (getHelpOption()) {
            System.out.println("show - Show the available publications");
            System.out.println("list - Show taken publications");
            System.out.println("take index/title - Take a publication");
            System.out.println("nextday - Move on to the next day");
            setHelpOption(false);
        }
        if (showOption) {
            System.out.println("**Available publications**");
            readerService.getPublicationRepository().getAll().forEach(System.out::println);
            showOption = false;
        }
        if (listOption) {
            System.out.println("**Taken publications**");
            readerService.getReaderPublications().getAll().forEach(System.out::println);
            listOption = false;
        }
        if (takeOption) {
            Pair response = readerService.getPublication(query, readerService.getPublicationRepository());
            System.out.println(response.value());
            if (!response.key()) {
                return;
            }

            response = readerService.takePublication();
            System.out.println(response.value());
            if (response.key()) {
                readerService.makeIssue(day, readInt(1, 28));
                System.out.println("Successfully taken the publication!");
            }
            takeOption = false;
        }
        if (returnOption) {
            Pair response = readerService.getPublication(query, readerService.getReaderPublications());
            System.out.println(response.value());
            if (!response.key()) {
                return;
            }

            readerService.returnPublication(day);
            returnOption = false;
            System.out.println("Successfully returned the publication!");
        }
        if (nextDayOption) {
            day += 1;
            System.out.println("The next day has come");
        }
    }
}
