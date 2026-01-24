package repository;

import domain.Book;

import java.util.ArrayList;

public class BookRepository implements PublicationRepository<Book> {
    private final ArrayList<Book> books = new ArrayList<>();

    @Override
    public void add(Book publication) {
        books.add(publication);
    }

    @Override
    public void print() {
        for (Book book : books) {
            System.out.print(book.getAuthor() + " ");
            System.out.print(book.getYear() + " ");
            System.out.print(book.getTitle() + " ");
            System.out.print(book.getPages() + " ");
            System.out.println();
        }
    }
}
