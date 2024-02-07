package schemas;

import data.Utils;

public class User extends Person {
    private final Utils utils = new Utils();
    private int id;
    private String username;
    private final UserType userType = UserType.User;
    private String hashedPassword;

    public int getId() {
        return id;
    }

    public String getPassword() {
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

    public User(String name, String surname, int age, String username, String password) {
        setName(name);
        setSurname(surname);
        setAge(age);
        setUsername(username);
        hashedPassword = utils.getHashedPassword(password);
        setHashedPassword(hashedPassword);
    }
}