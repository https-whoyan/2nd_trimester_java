package data.users.schemas;

import data.users.Utils;

public class User extends Person {
    private String username;
    private final UserType userType = UserType.User;
    private int marketOwnerID = -1;
    private String hashedPassword;


    public String getHashedPassword() {
        return this.hashedPassword;
    }

    public void setHashedPassword(String hashedPassword) {
        this.hashedPassword = hashedPassword;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUsername() {
        return username;
    }

    public UserType getUserType() {
        return userType;
    }

    public void setMarketOwnerID(int marketOwnerID) {
        this.marketOwnerID = marketOwnerID;
    }

    public int getMarketOwnerID() {
        return marketOwnerID;
    }

    public User() {
    }

    public User(String name, String surname, int age, String username, String password) {
        setName(name);
        setSurname(surname);
        setAge(age);
        setUsername(username);
        hashedPassword = Utils.getHashedPassword(password);
        setHashedPassword(hashedPassword);
    }


    @Override
    public String toString() {
        return this.getName() + " " + this.getSurname();
    }
}