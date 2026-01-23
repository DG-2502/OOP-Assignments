package factories;

import juniors.JuniorDeveloper;
import juniors.PythonJunior;
import middles.JavaScriptMiddle;
import middles.MiddleDeveloper;
import seniors.PythonSenior;
import seniors.SeniorDeveloper;

public class PythonCourse implements DeveloperCourse{
    @Override
    public SeniorDeveloper createSenior() {
        return new PythonSenior();
    }

    @Override
    public JuniorDeveloper createJunior() {
        return new PythonJunior();
    }

    @Override
    public MiddleDeveloper createMiddle() {
        return new JavaScriptMiddle();
    }
}
