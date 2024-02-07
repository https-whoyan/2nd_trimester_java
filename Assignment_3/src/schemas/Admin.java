package schemas;

public class Admin extends User {
    private final UserType userType = UserType.Admin;

    public Admin(String name, String surname, int age, String username, String password) {
        super(name, surname, age, username, password);
    }

    public Admin() {
    }

    @Override
    public UserType getUserType() {
        return userType;
    }
}