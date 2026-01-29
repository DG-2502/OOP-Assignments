package service;

import domain.human.Librarian;
import domain.human.Reader;
import domain.issue.Issue;
import domain.publications.Publication;
import repository.LibraryRepository;

public record LibrarianService(Librarian librarian, LibraryRepository library) {
    public void registerPublication(Publication publication) {
        library.addPublication(publication);
    }

    public void registerReader(Reader reader) {
        library.addReader(reader);
    }

    public void registerReader(String name, String last){
        library.addReader(name, last);
    }

    public void issuePublication(Reader reader, Publication publication){
        if (!library.hasReader(reader)) {
            System.out.println("No such reader registered!");
            System.out.println("Registering Reader: " + reader);
            registerReader(reader);
        }
        if (!library.hasPublication(publication)){
            System.out.println("No such publication: " + publication);
            System.out.println("Could not issue the publication to the reader");
        }
        else {
            System.out.println("***Issue the publication to the reader***");
            library.removePublication(publication);
            reader.addPublication(publication);
            library.addIssue(new Issue(reader, publication));
        }
    }

    public void issuePublication(String firstname, String lastname, String publication){
        Reader reader = library.getReader(firstname, lastname);
        Publication pub = library.getPublication(publication);
        if (reader == null){
            System.out.println("No such reader registered!");
            System.out.println("Registering Reader: " + firstname + " " + lastname);
            registerReader(firstname, lastname);
            reader = library.getReader(firstname, lastname);
        }
        if (pub == null){
            System.out.println("No such publication: " + publication);
            System.out.println("Could not issue the publication to the reader");
        }
        else {
            System.out.println("***Issue the publication to the reader***");
            library.removePublication(pub);
            reader.addPublication(pub);
            library.addIssue(new Issue(reader, pub));
        }
    }

    public void returnPublication(Reader reader, Publication publication){
        if (!library.hasReader(reader)){
            System.out.println("The reader is unregistered");
        }
        else if (!reader.hasPublication(publication)){
            System.out.println("The reader doesn't have such publication");
        }
        else {
            System.out.println("***Return publication to the library***");
            reader.returnPublication(publication);
            library.addPublication(publication);
        }
    }
}
