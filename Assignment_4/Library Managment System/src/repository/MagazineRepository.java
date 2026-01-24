package repository;

import domain.Magazine;

import java.util.ArrayList;

public class MagazineRepository implements PublicationRepository<Magazine>{
    private final ArrayList<Magazine> magazines = new ArrayList<>();

    @Override
    public void add(Magazine publication) {
        magazines.add(publication);
    }

    @Override
    public void print() {
        for (Magazine magazine : magazines){
            System.out.print(magazine.getAuthor() + " ");
            System.out.print(magazine.getYear() + " ");
            System.out.print(magazine.getTitle() + " ");
            System.out.println();
        }
    }
}
