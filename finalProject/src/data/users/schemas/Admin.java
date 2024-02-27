package data.users.schemas;

public class Admin extends User {
    private final UserType userType = UserType.Admin;

    public Admin(String name, String surname, int age, String username, int marketID, String password) {
        super(name, surname, age, username, password);
        setMarketOwnerID(marketID);
    }

    public Admin() {
    }

    @Override
    public UserType getUserType() {
        return userType;
    }
}