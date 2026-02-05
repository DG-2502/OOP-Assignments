package service;

import domain.human.Human;
import domain.human.Librarian;
import domain.human.Reader;
import domain.publications.Book;
import domain.publications.Disc;
import domain.publications.Magazine;
import domain.publications.Publication;
import repository.*;

import java.util.ArrayList;
import java.util.Scanner;

public class LMSService extends Service {
    private UserServiceFactory user;
    private final Repository<Librarian> librarianRepository;
    private final Repository<Reader> readerRepository;
    private final PublicationRepository publicationRepository;

    public LMSService() {
        this.librarianRepository = new LibrarianRepository();
        this.readerRepository = new ReaderRepository();
        this.publicationRepository = new PublicationRepository();
        publicationRepository.add(new Book("R.J. Darkwater", "A Culinary History of the Moon"));
        publicationRepository.add(new Book("Marisol Vega", "The City Where All Clocks Are Wrong"));
        publicationRepository.add(new Disc("Dr. Octavia Vance", "On the Taxonomy of Ghosts"));
        publicationRepository.add(new Disc("L.B. Merriweather", "A Compendium of Forgotten Tuesdays"));
        publicationRepository.add(new Magazine("Professor Alistair Finch", "The Last Postal Clerk of Prague"));
        publicationRepository.add(new Magazine("K.J. Thorne", "The Mosaic of Salt and Iron"));
    }

    public void run() {
        System.out.println("---Library Management System---");
        login();
        while (!user.getShouldExit()) {
            user.executeCommands();
            user.readInput();
        }
        user = null;
    }

    private void login() {
        if (user == null) {
            System.out.println("-Please log into the system-");
            Scanner scanner = new Scanner(System.in);
            while (true) {
                String request = scanner.nextLine().trim();
                if (request.matches("[a-zA-Z]+")) {
                    ArrayList<Human> humans = new ArrayList<>();
                    humans.addAll(readerRepository.find(request));
                    humans.addAll(librarianRepository.find(request));
                    Human human;
                    if (humans.isEmpty()) {
                        System.out.println("Could not find a user with such a name: " + request);
                        continue;
                    } else if (humans.size() == 1) {
                        human = humans.getFirst();
                    } else {
                        System.out.println("Found several users with such a name: " + request);
                        for (Human human1 : humans) {
                            System.out.println(humans.indexOf(human1) + " " + human1);
                        }
                        human = humans.get(readIndex(humans.size() - 1));
                    }
                    String className = human.getClass().getSimpleName();
                    if (className.equals("Reader")) {
                        user = new ReaderService((Reader) human, publicationRepository);
                    }
                    else if (className.equals("Librarian")){
                        System.out.println("Not implemented yet");
                        continue;
                    }
                    System.out.println("Logged in as: " + human);
                    break;
                } else {
                    System.out.println("Please enter the name of the user!");
                }
            }
        }
    }
}
