import data.DBWork;
import schemas.Admin;
import schemas.User;

import java.sql.SQLException;


public class Main {
    public static void main(String[] args) throws SQLException {
        User admin = new Admin(
                "Ян",
                "Невежин",
                14,
                "https_whoyan",
                "somePass"
        );

        DBWork worker = new DBWork();
        worker.deleteUser(admin);
    }
}