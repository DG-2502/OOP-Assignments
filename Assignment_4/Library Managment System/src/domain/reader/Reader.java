package domain.reader;

import repository.library.Library;

public class Reader {
    private String firstName;
    private String lastName;
    private Library takenPublications;

    public Reader(String firstName, String lastName, Library takenPublications) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.takenPublications = takenPublications;
    }
}
