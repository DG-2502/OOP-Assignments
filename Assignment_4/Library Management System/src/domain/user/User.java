package domain.user;

import domain.Entity;

abstract public class User extends Entity {
    private final String firstName;
    private final String lastName;
    private final String age;
    private final UserType userType;

    public User(String firstName, String lastName, String typeName) {
        super();
        this.userType = UserType.valueOf(typeName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = String.valueOf((10 + ((int) (Math.random() * 50))));
    }

    public User(String firstName, String lastName, String age, String typeName) {
        super();
        this.userType = UserType.valueOf(typeName);
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }

    public User(String typeName) {
        super();
        this.userType = UserType.valueOf(typeName);
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

    @Override
    public String getType() {
        return userType.name();
    }

    public static User createByType(UserType type, String[] info) {
        if (UserType.values()[0].equals(type)){
            return Reader.create(info);
        } else if (UserType.values()[1].equals(type)) {
            return Librarian.create(info);
        }
        return null;
    }

    public enum UserType {
        reader,
        librarian;
    }
}
