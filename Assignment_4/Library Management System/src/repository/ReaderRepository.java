package repository;

import domain.human.Reader;
import domain.publications.Publication;

import java.util.ArrayList;
import java.util.stream.Collectors;


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
            System.out.println(readers.indexOf(reader) + " " + reader);
        }
    }

    @Override
    public Reader getByID(int id) {
        try {
            return readers.get(id);
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public ArrayList<Reader> find(String name) {
        return (ArrayList<Reader>) readers.stream().filter(reader -> reader.getFirstName().equals(name)).collect(Collectors.toList());
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
