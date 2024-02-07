package data;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import java.util.Base64;
import java.util.Properties;

import java.sql.Connection;
import java.sql.DriverManager;

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

    public Connection GetConnector()  {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/java_assigment_3";

            Properties authorization = new Properties();
            authorization.put("user", "postgres");
            authorization.put("password", "postgres");

            return DriverManager.getConnection(url, authorization);
        } catch (Exception e) {
            System.err.println("Error accessing database!");
        }
        return null;
    }

    public Utils() {
    }
}