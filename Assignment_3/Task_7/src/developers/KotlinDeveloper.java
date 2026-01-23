package developers;

public class KotlinDeveloper extends JavaDeveloper {
    @Override
    public void study() {
        super.study();
        System.out.println("Изучает особенности котлина...");
        writeCode();
    }
}
