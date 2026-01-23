package creature;

import move.MoveLogic;
import sound.SoundLogic;

public class Cat extends Creature {
    public Cat(MoveLogic moveLogic, SoundLogic soundLogic) {
        super(moveLogic, soundLogic);
    }

    @Override
    public String getName() {
        return "Cat";
    }

    public void catchMice() {
        System.out.println(getName() + " is catching mice");
    }
}
