package presentation;

import data.users.schemas.User;
import data.users.schemas.UserType;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException, NumberFormatException {
        System.out.println("Добро пожаловать! Это проект по покупки товаров для обычных пользователей, и " +
                "добавлению товаров для администраторов магазина");

        User curUser = Auth.LogginUser();
        if (curUser == null) {
            System.out.println("Вы не авторизованы. Дальнейшая работа невозможна");
        }
        if (curUser.getUserType() == UserType.Admin) {
            AdminInteraction.Interaction(curUser);
        } else {
            UserInteraction.Interaction(curUser);
        }

        System.out.println("Спасибо за использование!");
    }
}