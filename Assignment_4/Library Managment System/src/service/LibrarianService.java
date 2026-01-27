package service;

import domain.human.Librarian;
import domain.human.Reader;
import domain.issue.Issue;
import domain.publications.Publication;

public record LibrarianService(Librarian librarian) {
    public void registerPublication(Publication publication) {
        librarian.addPublication(publication);
    }

    public void registerReader(Reader reader) {
        librarian.addReader(reader);
    }

    public void issuePublication(Reader reader, Publication publication){
        if (!librarian.hasReader(reader)) {
            System.out.println("No such reader registered!");
            System.out.println("Registering Reader: " + reader);
            registerReader(reader);
        }
        if (!librarian.hasPublication(publication)){
            System.out.println("No such publication: " + publication);
            System.out.println("Could not issue the publication to the reader");
        }
        else {
            System.out.println("Issue the publication to the reader");
            librarian.removePublication(publication);
            reader.addPublication(publication);
            librarian.addIssue(new Issue(reader, publication));
        }
    }

    public void returnPublication(Reader reader, Publication publication){
//        if (!librarian.hasReader(reader)){
//            System.out.println("The reader is unregistered");
//        }
//        else if (!reader.hasPublication(publication)){
//            System.out.println("The reader doesn't have such publication");
//        }
//        else if (!librarian.hasPublication(publication)){
//        }
    }
}
