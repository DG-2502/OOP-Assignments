package factories;

import juniors.JuniorDeveloper;
import middles.MiddleDeveloper;
import seniors.SeniorDeveloper;

public interface DeveloperCourse {
    JuniorDeveloper createJunior();
    MiddleDeveloper createMiddle();
    SeniorDeveloper createSenior();
}
