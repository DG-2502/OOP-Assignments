import domain.publications.*;
import domain.human.Librarian;
import domain.human.Reader;
import repository.PublicationRepository;
import service.LibrarianService;

void main() {
    System.out.println("---Data Management System---");
    LibrarianService firstLibrarian = new LibrarianService(new Librarian());

    Book firstBook = new Book(2002, "Book", "Book", 200);
    firstLibrarian.registerPublication(firstBook);
//    firstLibrarian.registerPublication(new Book());
//    firstLibrarian.registerPublication(new Book());
//    firstLibrarian.registerPublication(new Magazine());
    firstLibrarian.registerPublication(new Magazine());
    firstLibrarian.registerPublication(new Disc());

    Reader firstReader = new Reader("Reader", "Reader", new PublicationRepository());
    firstLibrarian.registerReader(firstReader);
    firstLibrarian.registerReader(new Reader());

    firstLibrarian.librarian().print();

    firstLibrarian.issuePublication(firstReader, firstBook);

    firstLibrarian.librarian().print();
}