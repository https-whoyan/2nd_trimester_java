package main;

import Developers.Developer;

import java.util.Scanner;

public class Main {
    private static Scanner getScanner() {
        return new Scanner(System.in);
    }

    public static void main(String[] args) {
        String typeOfCourse;
        Scanner scanner = getScanner();

        System.out.println("Введите тип курса.");
        System.out.println("Всего 4 вида: \nPython\nGolang\nJava\nTS");
        System.out.print("Тип курса: ");
        typeOfCourse = scanner.next();

        String nameOfCourse = typeOfCourse + " Blyat";
        Course anotherShitITCourse = new Course(nameOfCourse, typeOfCourse);
        // Создаем студента
        Developer newDev = anotherShitITCourse.devFactory.createDev("Рахат");
        anotherShitITCourse.appendNewDev(newDev);

        System.out.println();
        System.out.println("Выпустившийся студент пишет код...");
        // Пишем код студента...
        anotherShitITCourse.devs.get(0).writeCode();
    }
}