package data;

public class Auth {
    private final Utils utils = new Utils();

    public boolean AuthUser(String username, String password) {
        //тут будет запрос в бд из SelectFromDB;
        //...
        //допустим получил нужный хэш-пароль
        String hashedPassword = "92sdjfbhu4hgff";
        String enteredHashedPassword = utils.getHashedPassword(password);
        return hashedPassword.equals(enteredHashedPassword);
    }

    public Auth() {
    }
}
