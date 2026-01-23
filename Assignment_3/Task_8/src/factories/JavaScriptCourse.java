package factories;

import juniors.JavaScriptJunior;
import juniors.JuniorDeveloper;
import middles.JavaScriptMiddle;
import middles.MiddleDeveloper;
import seniors.JavaScriptSenior;
import seniors.SeniorDeveloper;

public class JavaScriptCourse implements DeveloperCourse{
    @Override
    public JuniorDeveloper createJunior() {
        return new JavaScriptJunior();
    }

    @Override
    public MiddleDeveloper createMiddle() {
        return new JavaScriptMiddle();
    }

    @Override
    public SeniorDeveloper createSenior() {
        return new JavaScriptSenior();
    }
}
