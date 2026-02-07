package console;

import service.ReaderService;

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
            return takeOption = readerService.getPublication(query, readerService.getPublications());
        }
        if (command.equals("return")) {
            return returnOption = readerService.getPublication(query, readerService.getReaderPublications());
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
            readerService.getPublications().print();
            showOption = false;
        }
        if (listOption) {
            System.out.println("**Taken publications**");
            readerService.getReaderPublications().print();
            listOption = false;
        }
        if (takeOption) {
            if (readerService.takePublication()) {
                System.out.println("Successfully taken the publication!");
            } else {
                System.out.println("Could not take a publication due to the reason above!");
            }
            takeOption = false;
        }
        if (returnOption) {
            readerService.returnPublication();
            returnOption = false;
        }
    }
}
