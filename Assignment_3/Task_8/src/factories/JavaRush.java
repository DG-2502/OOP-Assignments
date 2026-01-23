package factories;

import juniors.JavaJunior;
import juniors.JuniorDeveloper;
import middles.JavaMiddle;
import middles.MiddleDeveloper;
import seniors.JavaSenior;
import seniors.SeniorDeveloper;

public class JavaRush implements DeveloperCourse{
    @Override
    public MiddleDeveloper createMiddle() {
        return new JavaMiddle();
    }

    @Override
    public JuniorDeveloper createJunior() {
        return new JavaJunior();
    }

    @Override
    public SeniorDeveloper createSenior() {
        return new JavaSenior();
    }
}
