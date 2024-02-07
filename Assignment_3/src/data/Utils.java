package data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public final class Utils {
    public String getHashedPassword(String password) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("SHA-512");
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
        byte[] hash = md.digest(password.getBytes());
        return Base64.getEncoder().encodeToString(hash);
    }

    public boolean AuthUser(String username, String password) {
        //тут будет запрос в бд из SelectFromDB;
        //...
        //допустим получил нужный хэш-пароль
        String hashedPassword = "92sdjfbhu4hgff";
        String enteredHashedPassword = getHashedPassword(password);
        return hashedPassword.equals(enteredHashedPassword);
    }

    public void GetConnector() {
        //Тут напишу коннектор к бд
    }

    public Utils() {
    }
}