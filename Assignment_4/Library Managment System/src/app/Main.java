import domain.Book;
import domain.Disc;
import domain.Magazine;
import repository.BookRepository;
import repository.DiscRepository;
import repository.MagazineRepository;

void main() {
    System.out.println("---Data Management System---");
    Book book = new Book(100, "Jow", "World", 264);
    System.out.println(book.getTitle());
    System.out.println(book.getYear());
    System.out.println(book.getAuthor());
    System.out.println(book.getPages());

    BookRepository bookRepository = new BookRepository();
    bookRepository.add(new Book(2006, "Hwo", "Qio", 21));
    bookRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    bookRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    bookRepository.add(new Book(2016, "Hwoog", "Qdsio", 210));
    bookRepository.print();

    MagazineRepository magazineRepository = new MagazineRepository();
    magazineRepository.add(new Magazine(2022, "TO", "How to OOP"));
    magazineRepository.add(new Magazine(2022, "TO", "How to"));
    magazineRepository.add(new Magazine(2022, "TO", "How"));
    magazineRepository.print();

    DiscRepository discRepository = new DiscRepository();
    discRepository.add(new Disc(2023, "New", "H", "John"));
    discRepository.add(new Disc(2023, "New", "How to", "John"));
    discRepository.add(new Disc(2023, "New", "How", "John"));
    discRepository.print();
}