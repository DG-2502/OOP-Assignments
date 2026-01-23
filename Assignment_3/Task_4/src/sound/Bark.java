package sound;

public class Bark implements SoundLogic{
    @Override
    public void sound(String name) {
        System.out.println(name + " is barking");
    }
}
