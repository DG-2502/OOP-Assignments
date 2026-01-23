package factory;

import developers.Developer;
import developers.KotlinDeveloper;

public class KotlinCourse extends ProgrammingCourse{
    @Override
    public Developer createDeveloper() {
        return new KotlinDeveloper();
    }

    public KotlinCourse() {
        createDeveloper();
    }
}
