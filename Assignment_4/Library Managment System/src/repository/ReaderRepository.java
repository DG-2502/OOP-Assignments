package repository;

import domain.human.Reader;

import java.util.ArrayList;

public class ReaderRepository implements Repository<Reader> {
    private final ArrayList<Reader> readers = new ArrayList<>();

    @Override
    public void add(Reader reader) {
        readers.add(reader);
    }

    @Override
    public void remove(int i) {
        readers.remove(i);
    }

    @Override
    public boolean has(Reader reader) {
        return readers.contains(reader);
    }

    @Override
    public void print() {
        for (Reader reader : readers){
            System.out.println(reader);
        }
    }
}
