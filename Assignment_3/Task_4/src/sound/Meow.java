package sound;

public class Meow implements SoundLogic{
    @Override
    public void sound(String name) {
        System.out.println(name + " is meowing");
    }
}
