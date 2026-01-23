package factory;

import developers.Developer;
import developers.DotNetDeveloper;

public class DotNetCourse extends ProgrammingCourse {
    @Override
    public Developer createDeveloper() {
        return new DotNetDeveloper();
    }

    public DotNetCourse() {
        createDeveloper();
    }
}
