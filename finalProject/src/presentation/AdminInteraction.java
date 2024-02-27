package presentation;

import data.items.DB.ItemsDBWork;
import data.items.schemas.Item;
import data.users.DB.UserDBWork;
import data.users.schemas.User;

import java.util.ArrayList;
import java.util.Scanner;

public class AdminInteraction {
    private static final Scanner scanner = new Scanner(System.in);

    private static void addItems(User currUser) {
        System.out.print("Введите количество добавляемых товаров: ");
        int countOfNewItems = -1;
        try {
            countOfNewItems = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Вы неправильно ввели количество товаров");
            System.out.println("=================");
            return;
        }

        ArrayList<Item> newItems = new ArrayList<>();

        for (int i = 1; i <= countOfNewItems; i++) {
            System.out.println("Введите данные товара: ");
            System.out.print("Название: ");
            String newItemName = scanner.next();
            System.out.print("Количество: ");
            int countOfNewItem = -1;
            try {
                countOfNewItem = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Вы неправильно ввели количество добавляемого товара " + newItemName);
                break;
            }

            System.out.print("Цена за 1 штуку: ");
            int priceOfNewItem = -1;
            try {
                priceOfNewItem = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Вы неправильно ввели количество добавляемого товара " + newItemName);
                break;
            }

            Item newItem = new Item(
                    -1,
                    newItemName,
                    currUser.getMarketOwnerID(),
                    countOfNewItem,
                    priceOfNewItem
            );

            newItems.add(newItem);
        }

        if (newItems.size() != countOfNewItems) {
            System.out.println("Не все товары были введены правильно, " +
                    "добавление партии товаров не удалось");
        } else {
            for (Item newItem : newItems) {
                ItemsDBWork.insertItem(newItem);
            }

            System.out.println("Добавление " + countOfNewItems + " товара/ов произшло успешно!");
            System.out.println("=================");
        }
    }


    public static void Interaction(User currUser) {
        boolean isExit = false;
        while (!isExit) {
            int typeOfAction = -1;
            System.out.println("Выберите желаемое действие:");
            System.out.println("1: Поменять пароль.");
            System.out.println("2: Добавить товары (существующие товары прибавяться в количестве)");
            System.out.println("3: Вывести все товара магазина");
            System.out.println("4: Выйти с системы");
            System.out.print("Действие: ");

            try {
                typeOfAction = Integer.parseInt(scanner.next());
            } catch (NumberFormatException e) {
                System.out.println("Такого действия нету");
            }
            System.out.println("=================");
            switch (typeOfAction) {
                case 1:
                    System.out.println("Введите новый пароль:");
                    String newPassword = scanner.next();
                    UserDBWork.updatePassword(currUser, newPassword);
                    System.out.println("Пароль изменен.");
                    System.out.println("=================");
                    break;
                case 2:
                    addItems(currUser);
                    break;
                case 3:
                    ArrayList<Item> allMarketItems = ItemsDBWork.getAllItems(currUser.getMarketOwnerID());

                    System.out.println("Все " + allMarketItems.size() + " товары магазина: ");
                    int index = 1;
                    for (Item marketItem : allMarketItems) {
                        System.out.println(index + ": " + marketItem.getName() +
                                " в количестве " + marketItem.getCount() +
                                ". Стоймость одного: " + marketItem.getPrice());
                        index++;
                    }
                    System.out.println("=================");
                    break;
                case 4:
                    isExit = true;
            }
        }
    }
}
