package domain.issue;

import domain.publications.Publication;
import domain.human.Reader;

public class Issue {
    private final Reader receiver;
    private final Publication received;

    public Issue(Reader receiver, Publication received) {
        this.receiver = receiver;
        this.received = received;
    }

    @Override
    public String toString() {
        return receiver + "\n" + received;
    }

    public Publication getReceived() {
        return received;
    }

    public Reader getReceiver() {
        return receiver;
    }
}
