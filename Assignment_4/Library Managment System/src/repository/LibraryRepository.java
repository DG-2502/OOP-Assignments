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

    public LibraryRepository(LibrarianRepository librarianRepository, PublicationRepository publicationRepository, ReaderRepository readerRepository, IssueRepository issueRepository) {
        this.librarianRepository = librarianRepository;
        this.publicationRepository = publicationRepository;
        this.readerRepository = readerRepository;
        this.issueRepository = issueRepository;
    }

    public LibraryRepository() {
        this.librarianRepository = new LibrarianRepository();
        this.publicationRepository = new PublicationRepository();
        this.readerRepository = new ReaderRepository();
        this.issueRepository = new IssueRepository();
    }

    public void addLibrarian(Librarian librarian) {
        librarianRepository.add(librarian);
    }

    public void addPublication(Publication publication) {
        if (!publicationRepository.has(publication)) {
            this.publicationRepository.add(publication);
        }
    }

    public void addReader(Reader reader){
        if (!readerRepository.has(reader)) {
            this.readerRepository.add(reader);
        }
    }
    public void addReader(String name, String last){
        this.readerRepository.add(name, last);
    }

    public void addIssue(Issue issue){
        issueRepository.add(issue);
    }

    public boolean hasPublication(Publication publication){
        return publicationRepository.has(publication);
    }

    public boolean hasReader(Reader reader){
        return readerRepository.has(reader);
    }

    public void print(){
        System.out.println("**Publications**");
        publicationRepository.print();
        System.out.println("**Readers**");
        readerRepository.print();
        System.out.println("**Issues**");
        issueRepository.print();
    }
    public void removePublication(Publication publication){
        publicationRepository.remove(publication);
    }
    public Reader getReader(String name, String last){
        try {
//            return readerRepository.getReaders().stream().filter(s -> s.getFirstName().equalsIgnoreCase(name)).findFirst().get();
            return readerRepository.getReaders().stream().filter(s -> s.getFirstName().equalsIgnoreCase(name) && s.getLastName().equalsIgnoreCase(last)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }
    public Publication getPublication(String title){
        try {
            return publicationRepository.getPublications().stream().filter(s -> s.getTitle().equalsIgnoreCase(title)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }
}
