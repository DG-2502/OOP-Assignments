package repository;

import domain.human.Librarian;
import domain.human.Reader;
import domain.issue.Issue;
import domain.publications.Publication;

public class LibraryRepository {
    private final LibrarianRepository librarianRepository;
    private final PublicationRepository publicationRepository;
    private final ReaderRepository readerRepository;
    private final IssueRepository issueRepository;

    public LibraryRepository() {
        this.librarianRepository = new LibrarianRepository();
        this.publicationRepository = new PublicationRepository();
        this.readerRepository = new ReaderRepository();
        this.issueRepository = new IssueRepository();
    }

    public void addLibrarian(Librarian librarian) {
        librarianRepository.add(librarian);
    }

    public void addReader(Reader reader) {
        if (!readerRepository.has(reader)) {
            this.readerRepository.add(reader);
        }
    }

    public void addReader(String name, String last) {
        this.readerRepository.add(name, last);
    }

    public void addIssue(Issue issue) {
        issueRepository.add(issue);
    }

    public void print() {
        System.out.println("**Publications**");
        publicationRepository.print();
        System.out.println("**Readers**");
        readerRepository.print();
        System.out.println("**Issues**");
        issueRepository.print();
        System.out.println("**Librarians**");
        librarianRepository.print();
    }

    public Reader getReader(String name, String last) {
        try {
            return readerRepository.getReaders().stream().filter(s -> s.getFirstName().equalsIgnoreCase(name) && s.getLastName().equalsIgnoreCase(last)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }

    public boolean hasCopy(String title) {
        return publicationRepository.hasCopy(title);

    }

    public PublicationRepository getPublications() {
        return publicationRepository;
    }

    public ReaderRepository getReaders() {
        return readerRepository;
    }

    public LibrarianRepository getLibrarians() {
        return librarianRepository;
    }

    public IssueRepository getIssues() {
        return issueRepository;
    }
}
