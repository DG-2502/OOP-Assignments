package move;

public class Fly implements MoveLogic{
    @Override
    public void move(String name) {
        System.out.println(name + " is flying");
    }
}
