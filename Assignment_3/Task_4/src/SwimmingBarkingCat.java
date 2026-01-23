public class SwimmingBarkingCat implements Creature {
    @Override
    public void move() {
        System.out.println("Кошка гребет ластами.");
    }

    @Override
    public void sound() {
        System.out.println("Кошка лает.");
    }

    public void catchMice() {
        System.out.println("Кошка ловит мышей.");
    }
}
