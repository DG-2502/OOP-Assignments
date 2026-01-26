package domain.human;

abstract public class Human {
    private final String firstName;
    private final String lastName;

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
        return firstName + " " + lastName;
    }
}
