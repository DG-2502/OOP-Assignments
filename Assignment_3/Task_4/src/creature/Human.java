package creature;

import move.MoveLogic;
import sound.SoundLogic;

public class Human extends Creature{
    public Human(MoveLogic moveLogic, SoundLogic soundLogic) {
        super(moveLogic, soundLogic);
    }

    @Override
    public String getName() {
        return "Human";
    }
    public void searchMeaningOfLife(){
        System.out.println(getName() + " is searching for the meaning of life");
    }
}
