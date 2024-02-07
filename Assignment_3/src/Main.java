import data.DBWork;
import data.Utils;
import schemas.User;
import schemas.UserType;

import java.sql.SQLException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws SQLException, NumberFormatException {
        final Scanner scanner = new Scanner(System.in);
        final DBWork dbWork = new DBWork();
        final Utils utils = new Utils();

        System.out.println("Добро пожаловать! Это проект по авторизации");

        User curUser = null;
        boolean isAuth = false;
        while (curUser == null) {
            System.out.println("Вы можете авторизоваться или зарегистроваться");
            System.out.println("1: Авторизация");
            System.out.println("2: Регистрация");
            System.out.println();
            System.out.print("Введите желаемое действие: ");

            int RegOfAuth = Integer.parseInt(scanner.nextLine());
            switch (RegOfAuth) {
                case 1:
                    System.out.println("Введите ваши данные:");
                    System.out.print("Ваш логин (юзернаим): ");
                    String username = scanner.nextLine();
                    curUser = dbWork.getCurrUser(username);
                    if (curUser != null) {
                        if (curUser.getUserType() == UserType.Admin) {
                            System.out.println("Добро пожаловать, администратор " + curUser);
                        } else {
                            System.out.println("Добро пожаловать, " + curUser);
                        }
                        System.out.print("Для авторизации введите ваш пароль: ");
                        String password = scanner.next();
                        isAuth = utils.AuthUser(curUser, password);
                        if (isAuth) {
                            System.out.println("Вы успешно авторизованы!");
                        } else {
                            System.out.println("Вы не авторизованы.");
                        }
                    } else {
                        System.out.println("Такого пользователя нет в базе данных.");
                    }
                    break;
                case 2:
                    System.out.println("Введите ваши данные:");
                    System.out.print("Ваше имя: ");
                    String newUserName = scanner.next();
                    System.out.print("Ваша фамилия: ");
                    String newUserSurname = scanner.next();
                    System.out.print("Ваш возвраст: ");
                    int newUserAge = Integer.parseInt(scanner.next());
                    System.out.print("Ваш юзернеим: ");
                    String newUserUsername = scanner.next();
                    System.out.print("Ваш пароль: ");
                    String newUserPassword = scanner.next();
                    System.out.println();
                    System.out.println("Для завершения авторизации повторите ваш пароль:");
                    System.out.print("Ваш пароль: ");
                    String newUserRepeatedPassword = scanner.next();

                    if (newUserPassword.equals(newUserRepeatedPassword)) {
                        curUser = new User(
                                newUserName,
                                newUserSurname,
                                newUserAge,
                                newUserUsername,
                                newUserPassword
                        );
                        System.out.println("Вы зарегистрированы и авторизованы.");
                        dbWork.InsertUser(curUser);
                        isAuth = true;

                    } else {
                        System.out.println("Вы не завершили авторизацию");
                    }
                    break;
                default:
                    System.out.println("Такого действия нету");
                    System.exit(0);
                    break;
            }
        }

        while (true) {
            int typeOfAction;
            if (isAuth) {
                switch (curUser.getUserType()) {
                    case UserType.User:
                        System.out.println("Выберите желаемое действие:");
                        System.out.println("1: Поменять пароль.");
                        System.out.println("2: Выйти с системы");
                        typeOfAction = Integer.parseInt(scanner.next());
                        switch (typeOfAction) {
                            case 1:
                                System.out.println("Введите новый пароль:");
                                String newPassword = scanner.next();
                                dbWork.updatePassword(curUser, newPassword);
                                System.out.println("Пароль изменен.");
                                break;
                            case 2:
                                System.out.println("Спасибо за использование!");
                                System.exit(0);
                                break;
                            default:
                                System.out.println("Такого действия нету");
                        }
                        break;
                    case UserType.Admin:
                        System.out.println("Выберите желаемое действие:");
                        System.out.println("1: Поменять пароль.");
                        System.out.println("2: Удалить пользователя");
                        System.out.println("3: Выйти с системы");
                        typeOfAction = Integer.parseInt(scanner.next());
                        switch (typeOfAction) {
                            case 1:
                                System.out.println("Введите новый пароль:");
                                String newPassword = scanner.next();
                                dbWork.updatePassword(curUser, newPassword);
                                System.out.println("Пароль изменен.");
                                break;
                            case 2:
                                System.out.print("Введите никнеим удаляемого пользователя: ");
                                String deletedUserNickname = scanner.next();
                                User deletedUser = dbWork.getCurrUser(deletedUserNickname);
                                if (deletedUser != null) {
                                    if (deletedUser.getUsername().equals(curUser.getUsername())) {
                                        System.out.println("Не удаляй себя :)");
                                    } else {
                                        dbWork.deleteUser(deletedUser);
                                        System.out.println("Пользователь " + deletedUser + " удален.");
                                    }
                                } else {
                                    System.out.println("Пользователя не существует в базе данных");
                                }
                                break;
                            case 3:
                                System.out.println("Спасибо за использование!");
                                System.exit(0);
                            default:
                                System.out.println("Такого действия нету");
                        }
                        break;
                    default:
                        //...
                }
            } else {
                System.out.println("Вы не авторизованы, дальнейшая работа невозможна.");
                System.exit(0);
            }
        }
    }
}