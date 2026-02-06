package domain.user;

import domain.Entity;

abstract public class User extends Entity {
    private final String firstName;
    private final String lastName;
    private final int age = 10 + ((int) (Math.random() * 50));

    public User(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public User() {
        super();
        this.firstName = "Mike";
        this.lastName = "Sally";
    }

    @Override
    public String toString() {
        return super.toString() + firstName + " " + lastName + " " + age + "y.o";
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
