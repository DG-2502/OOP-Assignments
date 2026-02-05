package service;

import domain.human.Reader;
import domain.publications.Publication;
import repository.PublicationRepository;

import java.util.ArrayList;

public class ReaderService extends UserService {
    private final Reader reader;
    private final PublicationRepository publications;
    private boolean helpOption = true ;
    private boolean showOption = false;
    private boolean listOption = false;
    private boolean takeOption = false;
    private boolean returnOption = false;

    public ReaderService(Reader reader, PublicationRepository publications) {
        this.reader = reader;
        this.publications = publications;
    }

    @Override
    public boolean parseCommand(String command, String query) {
        if (super.parseCommand(command, query)) {
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
            return takeOption = getPublication(query, publications);
        }
        if (command.equals("return")) {
            return  returnOption = getPublication(query, reader.getPublications());
        }
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
            publications.print();
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
        if (returnOption) {
            returnPublication(getChosenPub());
            returnOption = false;
        }
    }

    private boolean getPublication(String query, PublicationRepository publications) {
        try {
            int index = Integer.parseInt(query);
            setChosenPub(publications.getByID(index));
            if (getChosenPub() == null) {
                System.out.println("Could not get publication with ID: " + index);
                return false;
            }
            System.out.println("Chosen publication: " + getChosenPub());
            return true;
        } catch (NumberFormatException e) {
            ArrayList<Publication> publications1 = publications.find(query);
            if (publications1.isEmpty()) {
                System.out.println("Could not find a publication with such a title: " + query);
                return false;
            } else if (publications1.size() == 1) {
                setChosenPub(publications1.getFirst());
                System.out.println("Chosen publication: " + getChosenPub());
                return true;
            } else {
                System.out.println("Found several publications with such a title: " + query);
                for (Publication publication : publications1) {
                    System.out.println(publications1.indexOf(publication) + " " + publication);
                }
                setChosenPub(publications1.get(readIndex(publications1.size() - 1)));
                System.out.println("Chosen publication: " + getChosenPub());
                return true;
            }
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
//            library.addIssue(new Issue(reader, taken));
            System.out.println("Should make an Issue");
            return true;
        }
        System.out.println("Could not issue a publication, no copy available at the moment!");
        return false;
    }

    public void returnPublication(Publication publication) {
        reader.getPublications().getPublications().remove(publication);
        publications.addNew(publication);
        System.out.println("Should close Issue");
    }
}
