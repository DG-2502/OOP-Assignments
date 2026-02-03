import domain.publications.*;
import domain.human.Librarian;
import domain.human.Reader;
import repository.LibraryRepository;
import repository.PublicationRepository;
import service.LMSService;
import service.LibrarianService;
import service.ReaderService;

void main() {
    System.out.println("---Data Management System---");
    LibraryRepository library = new LibraryRepository();
    Librarian librarian = new Librarian();
    library.addLibrarian(librarian);
    LibrarianService firstLibrarian = new LibrarianService(librarian, library);

    Book firstBook = new Book(2002, "Book", "Book", 200);
    firstLibrarian.registerPublication(firstBook);
    firstLibrarian.registerPublication(new Magazine());
    firstLibrarian.registerPublication(new Disc());

    Reader firstReader = new Reader("Reader", "Reader", new PublicationRepository());
    firstLibrarian.registerReader(firstReader);
    firstLibrarian.registerReader(new Reader());

    firstLibrarian.library().print();

    firstLibrarian.issuePublication("Reader", "Reader", "Book");
    firstLibrarian.library().print();

    firstLibrarian.returnPublication("Reader", "Reader", "Book");
    firstLibrarian.library().print();

    firstLibrarian.issuePublication("Mike", "sally", "Book");
    firstLibrarian.issuePublication("mike", "bally", "Book");

    System.out.println(library.getPublications().getByID(0));
    System.out.println(library.getPublications().getByID(1));
    System.out.println(library.getPublications().getByID(2));

    Reader reader = new Reader("NewReader", "NewReader", new PublicationRepository());
    firstLibrarian.registerReader(reader);
    ReaderService readerService = new ReaderService(reader, library);
    readerService.takePublication(library.getPublications().getByID(0));
    library.print();

    LMSService lmsService = new LMSService(firstLibrarian.library().getLibrarians(), firstLibrarian.library().getReaders(), firstLibrarian.library().getPublications(), firstLibrarian.library().getIssues());
    lmsService.run();
}