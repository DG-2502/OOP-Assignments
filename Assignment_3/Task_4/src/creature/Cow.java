package creature;

import move.MoveLogic;
import sound.SoundLogic;

public class Cow extends Creature{
    public Cow(MoveLogic moveLogic, SoundLogic soundLogic) {
        super(moveLogic, soundLogic);
    }

    @Override
    public String getName() {
        return "Cow";
    }
    public void giveMilk(){
        System.out.println(getName() + " is giving milk");
    }
}
