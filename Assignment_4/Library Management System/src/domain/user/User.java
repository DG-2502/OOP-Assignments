package domain.user;

import domain.Entity;

abstract public class User extends Entity {
    private final String firstName;
    private final String lastName;
    private final String age;

    public User(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = String.valueOf((10 + ((int) (Math.random() * 50))));
    }

    public User(String firstName, String lastName, String age) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User() {
        super();
        this.firstName = "Mike";
        this.lastName = "Sally";
        this.age = String.valueOf((10 + ((int) (Math.random() * 50))));
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
