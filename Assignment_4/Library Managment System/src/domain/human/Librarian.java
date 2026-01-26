package domain.human;

import repository.IssueRepository;
import repository.PublicationRepository;

public class Librarian extends Human{
    private static int ID = 0;
    private int id;
    private PublicationRepository publicationRepository;
    private final IssueRepository issueRepository;

    private Librarian() {
        super();
        this.issueRepository = new IssueRepository();
    }

    public Librarian(PublicationRepository publicationRepository) {
        this();
        this.id = ID++;
        this.publicationRepository = publicationRepository;
    }

    public Librarian(int id) {
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
}
