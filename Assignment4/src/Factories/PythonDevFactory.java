package Factories;

import Developers.Developer;
import Developers.PythonDev;

public class PythonDevFactory implements DevFactory {
    @Override
    public Developer createDev(String name) {
        return new PythonDev(name);
    }
}
