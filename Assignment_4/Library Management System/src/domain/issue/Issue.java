package domain.issue;

import domain.Entity;
import domain.publications.Publication;
import domain.user.Reader;

public class Issue extends Entity {
    private final Reader receiver;
    private final Publication received;
    private boolean closed = false;
    private final int dayTaken;
    private final int dayPlannedReturn;
    private int dayReturned;

    public Issue(int dayTaken, int dayPlannedReturn, Reader receiver, Publication received) {
        super();
        this.dayTaken = dayTaken;
        this.dayPlannedReturn = dayPlannedReturn;
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

    public void close(int day) {
        this.dayReturned = day;
        this.closed = true;
    }
}
