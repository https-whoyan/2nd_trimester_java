package presentation;

import data.items.DB.ItemsDBWork;
import data.items.schemas.Item;
import data.orders.DB.OrderDBWork;
import data.orders.schemas.Order;
import data.users.DB.UserDBWork;
import data.users.schemas.User;

import java.util.ArrayList;
import java.util.Scanner;

public class UserInteraction {
    private static final Scanner scanner = new Scanner(System.in);

    private static void orderItems(User currUser) {
        System.out.print("Введите количество покупаемых товаров: ");
        int countOfNewItems = -1;
        try {
            countOfNewItems = Integer.parseInt(scanner.next());
        } catch (NumberFormatException e) {
            System.out.println("Вы неправильно ввели количество товаров");
            System.out.println("=================");
            return;
        }

        ArrayList<Item> orderedItems = new ArrayList<>();

        for (int i = 1; i <= countOfNewItems; i++) {
            System.out.println("Введите название товара: ");
            System.out.print("Название: ");
            String newItemName = scanner.next();

            ArrayList<Item> availableItems = ItemsDBWork.getAllItems(newItemName);

            if (availableItems.isEmpty()) {
                System.out.println("В наличии нету доступного товара. Покупка данного товара отменена.");
                continue;
            }
            System.out.println("Доступная выборка: ");
            int index = 0;
            for (Item availableItem : availableItems) {
                System.out.println((index + 1) + ". Название: " + availableItem.getName() + ", количество: " +
                        availableItem.getCount() + ", цена: " + availableItem.getPrice());
                index++;
            }

            System.out.print("Выберете index приобретаемого товара: ");

            int orderedItemIndex = -1;
            try {
                orderedItemIndex = Integer.parseInt(scanner.next());
                if (orderedItemIndex < 0 || orderedItemIndex > availableItems.size()) {
                    throw new NumberFormatException();
                }

                orderedItems.add(availableItems.get(orderedItemIndex - 1));
            } catch (NumberFormatException e) {
                System.out.println("Вы неправильно ввели индекс товара");
                System.out.println("=================");
            }
        }

        for (Item newItem : orderedItems) {
            Order newOrder = new Order(
                    -1,
                    currUser.getUsername(),
                    newItem.getId(),
                    newItem.getMarket_id(),
                    newItem.getCount()
            );
            OrderDBWork.insertOrder(newOrder);
            ItemsDBWork.setItemCountZero(newItem.getId());
        }

        if (!orderedItems.isEmpty()) {
            System.out.println("Заказ произошел успешно!");
        }
        System.out.println("=================");
    }

    private static void outputOrdersSize(User currUser) {
        ArrayList<Order> userOrders = OrderDBWork.getAllOrders(currUser.getUsername());

        System.out.println("Количество ваших заказов: " + userOrders.size());
    }

    public static void Interaction(User currUser) {
        boolean isExit = false;
        while (!isExit) {
            int typeOfAction = -1;
            System.out.println("Выберите желаемое действие:");
            System.out.println("1: Поменять пароль.");
            System.out.println("2: Купить товары");
            System.out.println("3: Вывести количество покупок");
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
                    orderItems(currUser);
                    break;
                case 3:
                    outputOrdersSize(currUser);
                    System.out.println("=================");
                    break;
                case 4:
                    isExit = true;
            }
        }
    }
}
