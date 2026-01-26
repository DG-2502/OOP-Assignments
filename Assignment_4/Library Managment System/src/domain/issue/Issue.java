package domain.issue;

import domain.human.Librarian;
import domain.publications.Publication;
import domain.human.Reader;

public class Issue {
    private final Librarian granted;
    private final Reader receiver;
    private final Publication received;

    public Issue(Librarian granted, Reader receiver, Publication received) {
        this.granted = granted;
        this.receiver = receiver;
        this.received = received;
    }

    @Override
    public String toString() {
        return granted + "\n" + receiver + "\n" + received;
    }

    public Publication getReceived() {
        return received;
    }
}
