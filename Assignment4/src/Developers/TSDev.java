package Developers;

import main.Person;

public class TSDev extends Person implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Some amazing TS code");
    }

    public TSDev(String name) {
        super(name);
    }
}
