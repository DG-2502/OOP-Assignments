package domain.human;

import domain.issue.Issue;
import domain.publications.Publication;
import repository.IssueRepository;
import repository.PublicationRepository;
import repository.ReaderRepository;

import java.util.ArrayList;

public class Librarian extends Human {
    private static int ID = 0;
    private int id;
    private PublicationRepository publicationRepository;
    private final IssueRepository issueRepository;
    private final ReaderRepository readerRepository;

    public Librarian() {
        super();
        this.readerRepository = new ReaderRepository();
        this.issueRepository = new IssueRepository();
        this.publicationRepository = new PublicationRepository();
    }

    public Librarian(PublicationRepository publicationRepository) {
        this();
        this.id = ID++;
        this.publicationRepository = publicationRepository;
    }

    protected Librarian(int id) {
        this();
        this.id = id;
        this.publicationRepository = null;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return super.toString() + " " + id;
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
}
