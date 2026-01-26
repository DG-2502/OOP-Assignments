import domain.issue.Issue;
import domain.publications.*;
import domain.human.Librarian;
import domain.human.Reader;
import repository.*;

void main() {
    System.out.println("---Data Management System---");

    PublicationRepository publicationRepository = new PublicationRepository();
    publicationRepository.add(new Book(2006, "Hwo", "Qio", 21));
    publicationRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    publicationRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    publicationRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));

    publicationRepository.add(new Magazine(2022, "TO", "How to OOP"));
    publicationRepository.add(new Magazine(2022, "TO", "How to"));
    publicationRepository.add(new Magazine(2022, "TO", "How"));

    publicationRepository.add(new Disc(2023, "New", "H", "John"));
    publicationRepository.add(new Disc(2023, "New", "How to", "John"));
    publicationRepository.add(new Disc(2023, "New", "How", "John"));
    publicationRepository.print();

    LibrarianRepository librarianRepository = new LibrarianRepository();
    librarianRepository.add(new Librarian(publicationRepository));
    librarianRepository.add(new Librarian(publicationRepository));
    librarianRepository.add(new Librarian(publicationRepository));
    System.out.println(librarianRepository.getById(0));
    System.out.println(librarianRepository.getById(4));

    Reader reader = new Reader("M", "K", new PublicationRepository());

    IssueRepository issueRepository = new IssueRepository();
    issueRepository.add(new Issue(librarianRepository.getById(0), reader, new Book(12, "Ja", "Rilo", 100)));
    issueRepository.add(new Issue(librarianRepository.getById(0), reader, new Magazine(2009, "Matt", "Engineering")));
    issueRepository.add(new Issue(librarianRepository.getById(0), reader, new Disc(2009, "Matt", "Engineering", "Park")));
    System.out.println(issueRepository.getById(0));
    System.out.println(issueRepository.getById(1));
    System.out.println(issueRepository.getById(2));

    PubRepository pubRepository = new PubRepository();
    pubRepository.add(new Book());
    pubRepository.add(new Magazine());
    pubRepository.add(new Disc());
    pubRepository.print();
}