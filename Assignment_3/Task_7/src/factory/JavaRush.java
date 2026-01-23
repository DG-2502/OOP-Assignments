package factory;

import developers.Developer;
import developers.JavaDeveloper;

public class JavaRush extends ProgrammingCourse {
    @Override
    public Developer createDeveloper() {
        return new JavaDeveloper();
    }

    public JavaRush() {
        createDeveloper();
    }
}
