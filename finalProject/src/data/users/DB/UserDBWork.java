package data.users.DB;

import data.users.schemas.Admin;
import data.users.schemas.User;
import data.users.schemas.UserType;
import data.users.Utils;
import domain.SQLConnection;

import java.awt.desktop.SystemEventListener;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Objects;

public class UserDBWork {
    public static void insertUser(User user) {
        try {
            Statement statement = SQLConnection.getStatement();
            String query;
            if (user.getUserType() == UserType.User) {
                query = "INSERT INTO users VALUES ('" +
                        user.getName() + "', '" + user.getSurname() + "', " + user.getAge() + ", '" +
                        user.getUsername() + "', '" + user.getUserType() + "', NULL, '" +
                        user.getHashedPassword() + "')";
            } else {
                query = "INSERT INTO users VALUES ('" +
                        user.getName() + "', '" + user.getSurname() + "', " + user.getAge() + ", '" +
                        user.getUsername() + "', '" + user.getUserType() + "', " + user.getMarketOwnerID() + ", '" +
                        user.getHashedPassword() + "')";
            }
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }

    public static User getCurrUser(String username) {

        Statement statement = SQLConnection.getStatement();
        try {
            String query = "SELECT * " +
                    "FROM users " +
                    "WHERE username = '" + username + "'";
            ResultSet table = statement.executeQuery(query);
            table.beforeFirst();

            User currUser = new User();

            while (table.next()) {
                if (Objects.equals(table.getString(5), UserType.Admin.toString())) {
                    currUser = new Admin();
                    currUser.setMarketOwnerID(Integer.parseInt(table.getString(6)));
                }
                currUser.setName(table.getString(1));
                currUser.setSurname(table.getString(2));
                currUser.setAge(Integer.parseInt(table.getString(3)));
                currUser.setUsername(table.getString(4));
                currUser.setHashedPassword(table.getString(7));
                return currUser;
            }
        } catch (Exception e) {
            //...
        }
        return null;
    }

    public static void updatePassword(User currUser, String newPass) {
        try {
            Statement statement = SQLConnection.getStatement();
            String newHashPass = Utils.getHashedPassword(newPass);
            String currUsername = currUser.getUsername();
            String query = "UPDATE users SET hashed_password = '" + newHashPass + "'" +
                    " WHERE username = '" + currUsername + "'";
            statement.executeQuery(query);
        } catch (Exception e) {
            //...
        }
    }
}
