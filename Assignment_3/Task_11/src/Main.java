public class Main {

    public static void main(String[] args) {
        Entity entity = new LazyInitializer(1001);
        System.out.println(entity.getId());
        System.out.println(entity.find("John"));
    }
}
