public class CrawlingBarkingCow implements Creature {
    @Override
    public void move() {
        System.out.println("Корова ползет извиваясь.");
    }

    @Override
    public void sound() {
        System.out.println("Корова лает.");
    }

    public void giveMilk() {
        System.out.println("Корова дает молоко.");
    }
}
