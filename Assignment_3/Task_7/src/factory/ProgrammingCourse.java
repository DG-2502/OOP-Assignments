package factory;

import developers.Developer;

public abstract class ProgrammingCourse {
    public abstract Developer createDeveloper();
    public void educateStudent(){
        createDeveloper().study();
    }
}
