import repository.library.Library;
import domain.publications.Book;
import domain.publications.Disc;
import domain.publications.Magazine;
import domain.librarian.Librarian;
import repository.librarian.LibrarianRepository;
import repository.publications.PublicationRepository;

void main() {
    System.out.println("---Data Management System---");

    PublicationRepository<Book> bookRepository = new PublicationRepository<>();
    bookRepository.add(new Book(2006, "Hwo", "Qio", 21));
    bookRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    bookRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    bookRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    bookRepository.print();

    PublicationRepository<Magazine> magazineRepository = new PublicationRepository<>();
    magazineRepository.add(new Magazine(2022, "TO", "How to OOP"));
    magazineRepository.add(new Magazine(2022, "TO", "How to"));
    magazineRepository.add(new Magazine(2022, "TO", "How"));
    magazineRepository.print();

    PublicationRepository<Disc> discRepository = new PublicationRepository<>();
    discRepository.add(new Disc(2023, "New", "H", "John"));
    discRepository.add(new Disc(2023, "New", "How to", "John"));
    discRepository.add(new Disc(2023, "New", "How", "John"));
    discRepository.print();

    LibrarianRepository librarianRepository = new LibrarianRepository();
    Library library = new Library(bookRepository, magazineRepository, discRepository);
    librarianRepository.add(new Librarian(library));
    librarianRepository.add(new Librarian(library));
    librarianRepository.add(new Librarian(library));
    System.out.println(librarianRepository.getById(0));
    System.out.println(librarianRepository.getById(4));
}