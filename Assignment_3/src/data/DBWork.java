package data;

import schemas.User;

public class DBWork {
    private final Utils utils = new Utils();

    public void InsertUser(User user) {
        //Тут будет вставка в бд
    }

    public User getCurrUser(String username) {
        //Тут будет какой то запрос с бд..

        //Тут будет валидация под класс
        return null;
    }

    public void updatePassword(User user) {
        //Тут будет какой то запрос с бд..
    }

    public void deleteUser(User curUser, User deletedUser) {
        //Тут будет какой то запрос с бд..
    }

    public DBWork() {
    }
}
