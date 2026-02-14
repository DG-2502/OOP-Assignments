package console;

import domain.publications.Publication;
import repository.Repository;
import service.ReaderService;
import util.Pair;

public class ReaderConsole extends UserConsole {
    private final ReaderService readerService;
    private boolean showOption = false;
    private boolean listOption = false;
    private boolean takeOption = false;
    private boolean returnOption = false;

    public ReaderConsole(ReaderService readerService) {
        this.readerService = readerService;
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
            return takeOption = getPublication(query, readerService.getPublicationRepository());
        }
        if (command.equals("return")) {
            return returnOption = getPublication(query, readerService.getReaderPublications());
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
            takeOption = false;
            Pair response = readerService.takePublication();
            System.out.println(response.value());
            if (response.key()) {
                readerService.makeIssue(myDate.getDate(), myDate.inDays(readInt(1, 28)));
                System.out.println("Successfully taken the publication!");
            }
        }
        if (returnOption) {
            returnOption = false;
            readerService.returnPublication(myDate.getDate());
            System.out.println("Successfully returned the publication!");
        }
    }

    public boolean getPublication(String query, Repository<Publication> publicationRepository) {
        Pair response = readerService.getPublication(query, publicationRepository);
        System.out.println(response.value());
        return response.key();
    }
}
