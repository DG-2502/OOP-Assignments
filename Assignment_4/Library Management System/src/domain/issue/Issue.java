package domain.issue;

import domain.Entity;

public class Issue extends Entity {
    private final int readerId;
    private final int pubId;
    private boolean closed = false;
    private final String dateTaken;
    private final String datePlannedReturn;
    private String dateReturned;

    public Issue(String dateTaken, String datePlannedReturn, int readerId, int pubId) {
        super();
        this.dateTaken = dateTaken;
        this.datePlannedReturn = datePlannedReturn;
        this.readerId = readerId;
        this.pubId = pubId;
    }

    @Override
    public String toString() {
        return super.toString() + "\nReader ID: " + readerId + "\nPublication ID: " + pubId + "\nTaken on: " + dateTaken + "\nShould return on: " + (datePlannedReturn) + "\n" + (closed ? "Returned on: " + dateReturned + "\nStatus: CLOSED" : "Status: OPEN") + "\n----------------------------";
    }

    public int getPubId() {
        return pubId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void close(String dateReturned) {
        this.dateReturned = dateReturned;
        this.closed = true;
    }
}
