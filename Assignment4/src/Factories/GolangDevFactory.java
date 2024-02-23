package Factories;

import Developers.Developer;
import Developers.GolangDev;

public class GolangDevFactory implements DevFactory {
    @Override
    public Developer createDev(String name) {
        return new GolangDev(name);
    }
}
