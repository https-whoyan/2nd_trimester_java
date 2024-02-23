package main;

import Developers.Developer;

import java.util.Scanner;

public class Main {
    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static void main(String[] args) throws InterruptedException {
        // Введение констант
        String typeOfCourse;
        Scanner scanner = getScanner();

        // Вывод
        System.out.println("Введите тип нового курса.");
        System.out.println("Всего 4 вида: \nPython\nGolang\nJava\nTS");
        System.out.print("Тип курса: ");
        typeOfCourse = scanner.next();

        // Создаю курс
        String nameOfCourse = "top1 KZ " + typeOfCourse + " Course";
        Course anotherShitITCourse = new Course(nameOfCourse, typeOfCourse);
        // Создаем студента
        Developer newDev = anotherShitITCourse.devFactory.createDev("Рахат");
        anotherShitITCourse.appendNewDev(newDev);

        //Вывод
        System.out.println("________");
        System.out.println("Обучение на курсе " + anotherShitITCourse.getName() + "....");
        Thread.sleep(1500);
        System.out.println("________");

        // Вывод принципа библиотеки
        String messageToUser = "Выпустившийся студент " + anotherShitITCourse.devs.get(0).getName() + " пишет код...";
        System.out.println(messageToUser);
        // Пишем код студента...
        anotherShitITCourse.devs.get(0).writeCode();
    }
}