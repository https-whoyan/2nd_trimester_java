package Developers;

import main.Person;

public class PythonDev extends Person implements Developer {
    @Override
    public void writeCode() {
        System.out.println("ЛИИИИИИИСТ []][][][][]]][][][]][");
    }

    public PythonDev(String name) {
        super(name);
    }
}
