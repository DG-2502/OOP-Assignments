package service;

import domain.human.Librarian;
import domain.human.Reader;
import domain.issue.Issue;
import domain.publications.Publication;
import repository.LibraryRepository;

public record LibrarianService(Librarian librarian, LibraryRepository library) {
    public void registerPublication(Publication publication) {
        library.getPublications().add(publication);
    }

    public void addPublication(String title) {
        library.getPublications().add(title);
    }

    public void registerReader(Reader reader) {
        library.addReader(reader);
    }

    public void registerReader(String name, String last){
        library.addReader(name, last);
    }

    public void issuePublication(String firstname, String lastname, String title){
        Reader reader = library.getReader(firstname, lastname);
        boolean pub = library.hasCopy(title);
        if (reader == null){
            System.out.println("No such reader registered!");
            System.out.println("Registering Reader: " + firstname + " " + lastname);
            registerReader(firstname, lastname);
            reader = library.getReader(firstname, lastname);
        }
        if (!pub){
            System.out.println("No such publication available: " + title);
            System.out.println("Could not issue the publication to the reader");
        }
        else {
            System.out.println("***Issue the publication to the reader***");
            library.getPublications().remove(title);
            reader.addPublication(library().getPublications().clonePublication(title));
            reader.getPublications().remove(title);
            library.addIssue(new Issue(reader, library().getPublications().getPublication(title)));
        }
    }

    public void returnPublication(String name, String last, String title) {
        Reader reader = library.getReaders().getReader(name, last);
        if (reader == null){
            System.out.println("The reader is unregistered");
        }
        else if (!reader.getPublications().hasCopy(title)){
            System.out.println("The reader doesn't have such publication");
        }
        else {
            System.out.println("***Return publication to the library***");
            reader.getPublications().remove(title);
            if (!reader.getPublications().hasCopy(title)){
                reader.getPublications().delete(title);
            }
            library.getPublications().add(title);
        }
    }
}
