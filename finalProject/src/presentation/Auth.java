package presentation;

import data.market.DB.MarketDBWork;
import data.market.schemas.Market;
import data.users.schemas.Admin;
import data.users.schemas.User;
import data.users.Utils;
import data.users.DB.UserDBWork;
import data.users.schemas.UserType;

import java.util.Scanner;

public class Auth {
    private static final Scanner scanner = new Scanner(System.in);

    private static boolean AuthUser(User user, String password) {
        String enteredHashedPassword = Utils.getHashedPassword(password);
        return user.getHashedPassword().equals(enteredHashedPassword);
    }

    private static User Login() {
        User loginnedUser;
        System.out.println("Введите ваши данные:");
        System.out.print("Ваш логин (юзернаим): ");
        String username = scanner.nextLine();
        loginnedUser = UserDBWork.getCurrUser(username);
        if (loginnedUser != null) {
            if (loginnedUser.getUserType() == UserType.Admin) {
                Market adminMarket = MarketDBWork.getCurrMarket(loginnedUser.getMarketOwnerID());
                System.out.println("Добро пожаловать, администратор магазина " +
                        adminMarket.getName() + " " + loginnedUser);
            } else {
                System.out.println("Добро пожаловать, " + loginnedUser);
            }
            System.out.print("Для авторизации введите ваш пароль: ");
            String password = scanner.next();
            boolean isAuth = AuthUser(loginnedUser, password);
            if (isAuth) {
                System.out.println("Вы успешно авторизованы!");
                System.out.println("=================");
            } else {
                System.out.println("Вы не авторизованы.");
                System.out.println("=================");
                return null;
            }
        } else {
            System.out.println("Такого пользователя нет в базе данных.");
            return null;
        }
        return loginnedUser;
    }

    private static User RegisterNewAdmin() {
        User newUser;

        System.out.println("Введите ваши данные:");

        System.out.print("Ваше имя: ");
        String newUserName = scanner.next();
        System.out.print("Ваша фамилия: ");
        String newUserSurname = scanner.next();
        System.out.print("Ваш возвраст: ");
        int newUserAge = Integer.parseInt(scanner.next());
        System.out.print("Ваш юзернеим: ");
        String newUserUsername = scanner.next();

        System.out.print("Ввведите название, которым вы владеете\n" +
                "В базу данных будет добавлен новый магазин. Название: ");
        String marketName = scanner.next();
        MarketDBWork.insertMarket(marketName);
        Market newMarket = MarketDBWork.getCurrMarket(marketName);
        int marketID = newMarket.getId();

        System.out.print("Ваш пароль: ");
        String newUserPassword = scanner.next();
        System.out.println();
        System.out.println("Для завершения авторизации повторите ваш пароль:");
        System.out.print("Ваш пароль: ");
        String newUserRepeatedPassword = scanner.next();

        if (newUserPassword.equals(newUserRepeatedPassword)) {
            newUser = new Admin(
                    newUserName,
                    newUserSurname,
                    newUserAge,
                    newUserUsername,
                    marketID,
                    newUserPassword
            );
            System.out.println("Вы зарегистрированы и авторизованы как администратор магазина " + newMarket);
        } else {
            System.out.println("Вы не завершили регистрацию");
            return null;
        }

        return newUser;
    }

    private static User RegisterNewUser() {
        User newUser;

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
            newUser = new User(
                    newUserName,
                    newUserSurname,
                    newUserAge,
                    newUserUsername,
                    newUserPassword
            );
            System.out.println("Вы зарегистрированы и авторизованы как покупатель.");
        } else {
            System.out.println("Вы не завершили регистрацию");
            return null;
        }

        return newUser;
    }

    private static User RegisterNewPerson() {
        User newPerson;

        System.out.println("Выберете тип регистрации");
        System.out.println("1. Покупатель");
        System.out.println("2. Владелец магазина");
        System.out.print("Действие: ");
        int UserOfAdmin;
        try {
            UserOfAdmin = Integer.parseInt(scanner.nextLine());
        } catch (Exception e) {
            System.out.println("Такого действия нету");
            return null;
        }
        System.out.println("=================");
        switch (UserOfAdmin) {
            case 1:
                newPerson = RegisterNewUser();
                break;
            case 2:
                newPerson = RegisterNewAdmin();
                break;
            default:
                System.out.println("Такого действия нету");
                return null;
        }
        return newPerson;
    }

    public static User LogginUser() {
        User curUser = null;
        int RegOfAuth = -1;
        while (curUser == null) {
            if (RegOfAuth != 1 && RegOfAuth != 2) {
                System.out.println("Вы можете авторизоваться или зарегистроваться");
                System.out.println("1: Авторизация");
                System.out.println("2: Регистрация");
                System.out.println();
                System.out.print("Введите желаемое действие: ");

                try {
                    RegOfAuth = Integer.parseInt(scanner.nextLine());
                } catch (Exception e) {
                    System.out.println("Такого действия нету");
                    continue;
                }

                System.out.println("=================");
            }
            switch (RegOfAuth) {
                case 1:
                    curUser = Login();
                    break;
                case 2:
                    curUser = RegisterNewPerson();
                    UserDBWork.insertUser(curUser);
                    break;
                default:
                    System.out.println("Такого действия нету");
                    break;
            }
        }

        return curUser;
    }
}