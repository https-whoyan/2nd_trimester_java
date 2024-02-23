package Developers;

import main.Person;

public class GolangDev extends Person implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Some awesome Golang code...");
    }

    public GolangDev(String name) {
        super(name);
    }
}
