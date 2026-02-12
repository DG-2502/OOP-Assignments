package domain.user;

import domain.Entity;

abstract public class User extends Entity {
    private String firstName;
    private String lastName;
    private String age;
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

    public void update(String[] info) {
        String newName = info[0];
        if (!newName.isEmpty()) {
            this.firstName = newName;
        }
        String newLast = info[1];
        if (!newLast.isEmpty()) {
            this.lastName = newLast;
        }
        String newAge = info[2];
        if (!newAge.isEmpty()) {
            this.age = newAge;
        }
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
