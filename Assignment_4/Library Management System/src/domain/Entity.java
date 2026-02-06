package domain;

public abstract class Entity {
    private static int ID = 10;
    private final int id;

    public Entity() {
        this.id = ID++;
    }
    public Entity(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return id + " ";
    }
}
