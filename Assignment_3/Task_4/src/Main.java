import creature.*;
import move.*;
import sound.*;

public class Main {

    public static void main(String[] args) {
        FlyingSpeakingCow cow = new FlyingSpeakingCow();
        cow.move();
        cow.sound();
        cow.giveMilk();

        SwimmingMeowingHuman human = new SwimmingMeowingHuman();
        human.move();
        human.sound();
        human.searchMeaningOfLife();

        Cow flyingmeowingcow = new Cow(new Fly(), new Meow());
        flyingmeowingcow.howDoIMove();
        flyingmeowingcow.howDoISound();
        flyingmeowingcow.giveMilk();
    }
}
