public class LazyInitializer implements Entity {
    private int id;
    private VeryHeavyEntity entity;

    public LazyInitializer(int id) {
        this.id = id;
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String find(String query) {
        if (entity == null) {
            entity = VeryHeavyEntity.load(id);
        }
        return entity.find(query);
    }
}
