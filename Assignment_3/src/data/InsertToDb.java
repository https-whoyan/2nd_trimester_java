package data;

import schemas.User;

public class InsertToDb {
    private final Utils utils = new Utils();

    public void InsertUser(User user)  {
        String hashedPassword = utils.getHashedPassword(user.getPassword());
        System.out.println(hashedPassword);
        System.out.println(user.getUserType());

        //Тут будет вставка в бд
    }

    public InsertToDb() {
    }
}
