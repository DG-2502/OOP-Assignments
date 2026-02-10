package domain.issue;

import domain.Entity;

public class Issue extends Entity {
    private final int readerId;
    private final int pubId;
    private boolean closed = false;
    private final int dayTaken;
    private final int dayPlannedReturn;
    private int dayReturned;

    public Issue(int dayTaken, int dayPlannedReturn, int readerId, int pubId) {
        super();
        this.dayTaken = dayTaken;
        this.dayPlannedReturn = dayPlannedReturn;
        this.readerId = readerId;
        this.pubId = pubId;
    }

    @Override
    public String toString() {
        return super.toString() + "\nReader ID: " + readerId + "\nPublication ID: " + pubId + "\nDay taken: " + dayTaken + "\nPlanned return day " + (dayTaken + dayPlannedReturn) + "\n" + (closed ? "day returned " + dayReturned + " closed" : "open");
    }

    public int getPubId() {
        return pubId;
    }

    public int getReaderId() {
        return readerId;
    }

    public void close(int day) {
        this.dayReturned = day;
        this.closed = true;
    }
}
