package service;

import domain.human.Librarian;
import domain.human.Reader;
import domain.issue.Issue;
import domain.publications.Publication;
import repository.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LMSService {
    private int displayOption = 0;
    private int helpOption = 1;
    private Reader chosenReader;
    private Publication chosenPub;
    private final Repository<Librarian> librarianRepository;
    private final Repository<Reader> readerRepository;
    private final Repository<Publication> publicationRepository;
    private final Repository<Issue> issueRepository;

    public LMSService() {
        this.librarianRepository = new LibrarianRepository();
        this.readerRepository = new ReaderRepository();
        this.publicationRepository = new PublicationRepository();
        this.issueRepository = new IssueRepository();
    }

    public LMSService(Repository<Librarian> libraryRepository, Repository<Reader> readerRepository, Repository<Publication> publicationRepository, Repository<Issue> issueRepository) {
        this.librarianRepository = libraryRepository;
        this.readerRepository = readerRepository;
        this.publicationRepository = publicationRepository;
        this.issueRepository = issueRepository;
    }

    public void run() {
        System.out.println("---Library Management System---");
        executeCommands();
        while (true) {
            readInput();
            executeCommands();
        }
    }

    private void readInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine().trim();
            if (request.matches("[a-zA-Z]+\\s+\\d+")) {
                String[] split = request.split("\\s+");
                if (parseCommand(split[0].toLowerCase(), Integer.parseInt(split[1]))) {
                    break;
                }

            } else if (request.matches("[a-zA-Z]+\\s+[a-zA-Z]+")) {
                String[] split = request.split("\\s+");
                if (parseCommand(split[0].toLowerCase(), split[1])) {
                    break;
                }
            } else if (request.matches("[a-zA-Z]+")) {
                if (parseCommand(request.toLowerCase(), 0)) {
                    break;
                }
            } else {
                System.out.println("Could not parse the command, please provide the following format: command option");
            }
        }
    }

    private int readIndex(int size) {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String request = scanner.nextLine().trim();
            if (request.matches("\\d+")) {
                int index = Integer.parseInt(request);
                if (size >= index && index >= 0) {
                    return index;
                }
            } else {
                System.out.println("Please type an index between 0 and " + size);
            }

        }
    }

    private boolean parseCommand(String command, int index) {
        if (command.equals("display")) {
            if ((index < 0) || (index > 5)) {
                System.out.print("The index is incorrect, possible values are: ");
                for (int i = 0; i < 5; i++) {
                    System.out.print((i + 1) + " ");
                }
                System.out.println();
                return false;
            }
            displayOption = index;
            return true;
        } else if (command.equals("help")) {
            helpOption = 1;
            return true;
        } else if (command.equals("cp")) {
            chosenPub = publicationRepository.getByID(index);
            if (chosenPub == null) {
                System.out.println("Could not get publication with ID: " + index);
                return false;
            }
            System.out.println("Chosen publication: " + chosenPub);
            return true;
        } else if (command.equals("cr")) {
            chosenReader = readerRepository.getByID(index);
            if (chosenReader == null) {
                System.out.println("Could not get reader with ID: " + index);
                return false;
            }
            System.out.println("Chosen reader: " + chosenReader);
            return true;
        }
        System.out.println("Could not parse the command, type 'help' to see the list of possible commands");
        return false;
    }

    private boolean parseCommand(String command, String option) {
        if (command.equals("cp")) {
            ArrayList<Publication> publications = publicationRepository.find(option);
            if (publications.isEmpty()) {
                System.out.println("Could not find a publication with such title: " + option);
                return false;
            } else if (publications.size() == 1) {
                chosenPub = publications.getFirst();
                System.out.println("Chosen publication: " + chosenPub);
                return true;
            } else {
                System.out.println("Found several publications with such title: " + option);
                for (Publication publication : publications) {
                    System.out.println(publications.indexOf(publication) + " " + publication);
                }
                chosenPub = publications.get(readIndex(publications.size() - 1));
                System.out.println("Chosen publication: " + chosenPub);
                return true;
            }
        } else if (command.equals("cr")) {
            ArrayList<Reader> readers = readerRepository.find(option);
            if (readers.isEmpty()) {
                System.out.println("Could not find a reader with such name: " + option);
                return false;
            } else if (readers.size() == 1) {
                chosenReader = readers.getFirst();
                System.out.println("Chosen reader: " + chosenReader);
                return true;
            } else {
                System.out.println("Found several readers with such title: " + option);
                for (Reader reader : readers) {
                    System.out.println(readers.indexOf(reader) + " " + reader);
                }
                chosenReader = readers.get(readIndex(readers.size() - 1));
                System.out.println("Chosen reader: " + chosenReader);
                return true;
            }
        }
        return false;
    }

    private void executeCommands() {
        switch (helpOption) {
            case 1:
                System.out.println("-Available commands-");
                System.out.println("Display: Choose displaying options\n  1 Publications\n  2 Librarians\n  3 Readers\n  4 Issues\n  5 All ");
                System.out.println("Help: Show help");
                helpOption = 0;
                break;
        }
        switch (displayOption) {
            case 1:
                System.out.println("**Publications**");
                publicationRepository.print();
                break;
            case 2:
                System.out.println("**Librarians**");
                librarianRepository.print();
                break;
            case 3:
                System.out.println("**Readers**");
                readerRepository.print();
                break;
            case 4:
                System.out.println("**Issues**");
                issueRepository.print();
                break;
            case 5:
                System.out.println("**Publications**");
                publicationRepository.print();
                System.out.println("**Librarians**");
                librarianRepository.print();
                System.out.println("**Readers**");
                readerRepository.print();
                System.out.println("**Issues**");
                issueRepository.print();
                break;
        }
        displayOption = 0;
    }
}
