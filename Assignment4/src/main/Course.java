package main;

import Developers.Developer;
import Factories.*;

import java.util.ArrayList;
import java.util.List;

public class Course {
    private String name;
    public DevFactory devFactory; //вот это намек, ай я яй)))
    public List<Developer> devs = new ArrayList<>();

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Course(String name, String typeOfCourse) {
        this.name = name;

        switch (typeOfCourse) {
            case "TS":
                this.devFactory = new TSDevFactory();
                break;
            case "Golang":
                this.devFactory = new GolangDevFactory();
                break;
            case "Python":
                this.devFactory = new PythonDevFactory();
                break;
            case "Java":
                this.devFactory = new JavaDevFactory();
                break;
            default:
                throw new RuntimeException("Направления " + typeOfCourse + " не существует");
        }

    }

    public void appendNewDev(Developer newDev) {

        devs.add(newDev);
    }

    public void removeDev(Developer removedDev) {
        devs.remove(removedDev);
    }
}

