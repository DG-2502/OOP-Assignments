package service;

import domain.human.Reader;
import domain.issue.Issue;
import domain.publications.Publication;
import repository.LibraryRepository;

import java.util.ArrayList;

public class ReaderService extends UserService {
    private final Reader reader;
    private final LibraryRepository library;
    private boolean helpOption = true;
    private boolean showOption = false;
    private boolean listOption = false;
    private boolean takeOption = false;

    public ReaderService(Reader reader, LibraryRepository library) {
        this.reader = reader;
        this.library = library;
        System.out.println("Logged in as:" + reader.getFirstName());
    }

    @Override
    public boolean parseCommand(String command, String option) {
        if (super.parseCommand(command, option)) {
            return true;
        }
        if (command.equals("help")) {
            return helpOption = true;
        }
        if (command.equals("show")) {
            return showOption = true;
        }
        if (command.equals("list")) {
            return listOption = true;
        }
        if (command.equals("take")) {
            try {
                int index = Integer.parseInt(option);
                setChosenPub(library.getPublications().getByID(index));
                if (getChosenPub() == null) {
                    System.out.println("Could not get publication with ID: " + index);
                    return false;
                }
                System.out.println("Chosen publication: " + getChosenPub());
                return takeOption = true;
            } catch (NumberFormatException e) {
                ArrayList<Publication> publications = library.getPublications().find(option);
                if (publications.isEmpty()) {
                    System.out.println("Could not find a publication with such a title: " + option);
                    return false;
                } else if (publications.size() == 1) {
                    setChosenPub(publications.getFirst());
                    System.out.println("Chosen publication: " + getChosenPub());
                    return takeOption = true;
                } else {
                    System.out.println("Found several publications with such a title: " + option);
                    for (Publication publication : publications) {
                        System.out.println(publications.indexOf(publication) + " " + publication);
                    }
                    setChosenPub(publications.get(readIndex(publications.size() - 1)));
                    System.out.println("Chosen publication: " + getChosenPub());
                    return takeOption = true;
                }
            }
        }
//        if (command.equals("return")) {
//            try {
//                int index = Integer.parseInt(option);
//                setChosenPub(reader.getPublications().getByID(index));
//                if (getChosenPub() == null) {
//                    System.out.println("Could not get publication with ID: " + index);
//                    return false;
//                }
//                System.out.println("Chosen publication: " + getChosenPub());
//                return takeOption = true;
//            } catch (NumberFormatException e) {
//                throw new RuntimeException(e);
//            }
//        }
        System.out.println("Could not find the command, type 'help' to see the list of possible commands");
        return false;
    }

    @Override
    public void executeCommands() {
        super.executeCommands();
        if (helpOption) {
            System.out.println("Available commands:");
            System.out.println("help             - Show available commands");
            System.out.println("show             - Show the available publications");
            System.out.println("list             - Show taken publications");
            System.out.println("take index/title - Take a publication");
            System.out.println("exit             - Log out of the system");
            helpOption = false;
        }
        if (showOption) {
            System.out.println("**Available publications**");
            library.getPublications().print();
            showOption = false;
        }
        if (listOption) {
            System.out.println("**Taken publications**");
            reader.getPublications().print();
            listOption = false;
        }
        if (takeOption) {
            if (takePublication(getChosenPub())) {
                System.out.println("Successfully taken the publication!");
            } else {
                System.out.println("Could not take a publication due to the reason above!");
            }
            takeOption = false;
        }
    }

    public boolean takePublication(Publication publication) {
        if (reader.hasPublication(publication)) {
            System.out.println("You already have a copy of this publication");
            return false;
        }
        if (publication.decrease()) {
            Publication taken = publication.clone();
            reader.addPublicationNew(taken);
            library.addIssue(new Issue(reader, taken));
            return true;
        }
        System.out.println("Could not issue a publication, no copy available at the moment!");
        return false;
    }

    public void returnPublication() {

    }
}
