import data.InsertToDb;
import schemas.Admin;
import schemas.User;


public class Main {
    public static void main(String[] args) {
        User admin = new Admin(
                "Ян",
                "Невежин",
                14,
                "https_whoyan",
                "somePass"
        );

        InsertToDb dbInsertWorker = new InsertToDb();
        dbInsertWorker.InsertUser(admin);
        System.out.println(admin.getUserType());
    }
}