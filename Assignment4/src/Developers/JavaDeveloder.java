package Developers;

import main.Person;

public class JavaDeveloder extends Person implements Developer {
    @Override
    public void writeCode() {
        System.out.println("Some Java shitCode...");
    }

    public JavaDeveloder(String name) {
        super(name);
    }
}
