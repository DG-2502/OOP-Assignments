package repository;

import domain.human.Reader;

import java.util.ArrayList;

public class ReaderRepository implements Repository<Reader> {
    private final ArrayList<Reader> readers = new ArrayList<>();

    @Override
    public void add(Reader reader) {
        readers.add(reader);
    }

    public void add(String name, String last){
        readers.add(new Reader(name, last));
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

    public ArrayList<Reader> getReaders() {
        return readers;
    }
    public Reader getReader(String name, String last){
        try {
            return readers.stream().filter(s -> s.getFirstName().equalsIgnoreCase(name) && s.getLastName().equalsIgnoreCase(last)).findFirst().get();
        } catch (Exception e) {
            return null;
        }
    }
}
