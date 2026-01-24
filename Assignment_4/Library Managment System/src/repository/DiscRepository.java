package repository;

import domain.Book;
import domain.Disc;

import java.util.ArrayList;

public class DiscRepository implements PublicationRepository<Disc> {
    private final ArrayList<Disc> discs = new ArrayList<>();

    @Override
    public void add(Disc publication) {
        discs.add(publication);
    }

    @Override
    public void print() {
        for (Disc disc : discs) {
            System.out.print(disc.getAuthor() + " ");
            System.out.print(disc.getYear() + " ");
            System.out.print(disc.getTitle() + " ");
            System.out.print(disc.getNarrator() + " ");
            System.out.println();
        }
    }
}
