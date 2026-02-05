package repository;

import domain.human.Reader;

import java.util.ArrayList;
import java.util.stream.Collectors;


public class ReaderRepository implements Repository<Reader> {
    private final ArrayList<Reader> readers = new ArrayList<Reader>(){{
        add(new Reader("Marley", "Buck"));
        add(new Reader("Carley", "Robinson"));
        add(new Reader("Carley", "Robinson"));
        add(new Reader("Joshua", "Estes"));
    }};

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
        return (ArrayList<Reader>) readers.stream().filter(reader -> reader.getFirstName().equalsIgnoreCase(name) || reader.getLastName().equalsIgnoreCase(name)).collect(Collectors.toList());
    }
}
