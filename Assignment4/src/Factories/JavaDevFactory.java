package Factories;

import Developers.Developer;
import Developers.JavaDeveloder;

public class JavaDevFactory implements DevFactory {
    @Override
    public Developer createDev(String name) {
        return new JavaDeveloder(name);
    }
}
