package domain.human;

import java.util.Random;
import java.util.random.RandomGenerator;

abstract public class Human {
    private final String firstName;
    private final String lastName;
    private final int age = 10 + ((int) (Math.random() * 50));

    public Human(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Human() {
        this.firstName = "Mike";
        this.lastName = "Sally";
    }

    @Override
    public String toString() {
        return firstName + " " + lastName + " " + age + "y.o";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
