package Factories;

import Developers.Developer;
import Developers.TSDev;

public class TSDevFactory implements DevFactory {
    @Override
    public Developer createDev(String name) {
        return new TSDev(name);
    }
}
